package com.example.core.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.example.core.android.viewmodel.BaseCommand
import com.example.core.android.viewmodel.CommandType
import com.example.core.corountines.throttleFist
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * BaseViewModel is an abstract class that extends ViewModel and provides a base implementation
 * for handling commands and actions using Kotlin coroutines and flows.
 *
 * @param Command The type of command that extends BaseCommand.
 * @param Action The type of action.
 */
abstract class BaseViewModel<Command : BaseCommand, Action : Any> : ViewModel() {

    private val _actions = MutableSharedFlow<Action>()
    val actions: SharedFlow<Action> get() = _actions
    private val commands = MutableSharedFlow<Command>()


    private val throttableCommands: Flow<Command>
        get() = commands.filter { command -> command.type == CommandType.Throttable }
            .throttleFist(500)
            .buffer(capacity = 64)

    private val immediateCommands: Flow<Command>
        get() = commands.filter { it.type == CommandType.Immediate }

    @OptIn(ExperimentalCoroutinesApi::class)
    private val commandsStream: Flow<Command>
        get() = flowOf(throttableCommands, immediateCommands).flattenMerge()

    protected val viewModelScope: CoroutineScope by lazy {
        CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate + CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        })
    }

    init {
        viewModelScope.launch {
            commandsStream.onEach { command ->
                withContext(Dispatchers.Main.immediate) {
                    runCatching { command.handle() }
                        .onFailure { exception -> exception.printStackTrace() }
                        .getOrThrow()
                }
            }
                .retry(1)
                .collect()
        }
    }


    abstract fun Command.handle(): Unit

    protected fun Action.send() {
        viewModelScope.launch {
            _actions.emit(this@send)
        }
    }

    protected fun Action.sendLaunch() {
        viewModelScope.launch {
            _actions.emit(this@sendLaunch)
        }
    }
    fun trigger(command: Command) {
        viewModelScope.launch {
            commands.emit(command)
        }
    }


    @CallSuper
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    fun <T, R> StateFlow<T>.mapState(
        map: (T) -> R,
    ) = mapLatest { state-> map(state) }.stateIn(viewModelScope, SharingStarted.Eagerly, map(value))
}

