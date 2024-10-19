package com.musicplayer.user.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.musicplayer.core.android.viewmodel.BaseCommand
import com.musicplayer.core.android.viewmodel.CommandType
import com.musicplayer.core.viewmodel.BaseViewModel
import com.musicplayer.user.domain.entity.LoginResult
import com.musicplayer.user.domain.entity.RegisterResult
import com.musicplayer.user.domain.usecase.UserGetLoginDataUseCase
import com.musicplayer.user.domain.usecase.UserGetLoginDataUseCase.LoginScreenData
import com.musicplayer.user.domain.usecase.UserLoginUseCase
import com.musicplayer.user.domain.usecase.UserRegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserLoginViewModel(
    private val getLoginDataUseCase: UserGetLoginDataUseCase,
    private val userRegisterUseCase: UserRegisterUseCase,
    private val userLoginUseCase: UserLoginUseCase,
) : BaseViewModel<UserLoginViewModel.Command, UserLoginViewModel.Action>() {

    private val state = MutableStateFlow(State())
    val viewState: StateFlow<ViewState> = state.mapState { state ->
        state.toViewState()
    }

    var userEmail by mutableStateOf("")
        private set
    var userPassword by mutableStateOf("")
        private set

    init {
        val data = getLoginDataUseCase.execute()
        state.update { state -> state.copy(data = data) }
    }

    override fun Command.handle() {
        when (this) {
            is Command.OnLoginCtaClicked -> login()
            is Command.OnRegisterCtaClicked -> register()
            Command.OnContinueAsGuestCtaClicked -> TODO()
            is Command.OnEmailChanged -> onEmailChanged(email = email)
            is Command.OnPasswordChanged -> onPasswordChanged(password = password)
        }
    }

    private fun onEmailChanged(email: String) {
        userEmail = email
    }

    private fun onPasswordChanged(password: String) {
        userPassword = password
    }


    private fun login() {
        viewModelScope.launch {
            val result = userLoginUseCase.execute(email = userEmail, password = userPassword)
            when (result) {
                is LoginResult.Success -> onLoginSuccess()
                is LoginResult.Error -> onLoginFailure()
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            val result =
                userRegisterUseCase.execute(email = userEmail, password = userPassword)
            when (result) {
                RegisterResult.Success -> onRegisterSuccess()
                is RegisterResult.Error -> result.onRegisterFailure()
            }
        }
    }


    private fun onLoginSuccess() {
        TODO()
    }

    private fun onLoginFailure() {
        TODO()
    }

    private fun onRegisterSuccess() {
        TODO()
    }

    private fun RegisterResult.Error.onRegisterFailure() {
        TODO()
    }

    private fun State.toViewState(): ViewState {
        return ViewState(
            title = data?.title.orEmpty(),
            description = data?.description.orEmpty(),
            loginCtaTitle = data?.loginCtaTitle.orEmpty(),
            signUpCtaTitle = data?.signUpCtaTitle.orEmpty(),
            continueAsGuestCtaTitle = data?.continueAsGuestCtaTitle.orEmpty(),
            separator = data?.separator.orEmpty(),
            forgotPassword = data?.forgotPassword.orEmpty()
        )
    }

    sealed class Action {
        data object PrintLog : Action()
    }

    sealed class Command(override val type: CommandType = CommandType.Throttable) : BaseCommand {
        data object OnLoginCtaClicked : Command()
        data object OnRegisterCtaClicked : Command()
        data object OnContinueAsGuestCtaClicked : Command()
        data class OnEmailChanged(val email: String) : Command(type = CommandType.Immediate)
        data class OnPasswordChanged(val password: String) : Command(type = CommandType.Immediate)
    }


    private data class State(
        val data: LoginScreenData? = null
    )

    data class ViewState(
        val title: String,
        val description: String,
        val loginCtaTitle: String,
        val signUpCtaTitle: String,
        val continueAsGuestCtaTitle: String,
        val separator: String,
        val forgotPassword: String,
    )
}

