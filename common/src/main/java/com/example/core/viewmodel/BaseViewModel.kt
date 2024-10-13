//package com.example.core.android.viewmodel
//
//import androidx.lifecycle.ViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//abstract class BaseViewModel<Command : BaseCommand, Action: Any> : ViewModel() {
//
//    private val _actions = MutableStateFlow<Action?>(null)
//    val actions: StateFlow<Action?> get() = _actions
//
//    protected fun Action.send(){
//        _actions.value = this
//    }
//
//    protected fun Action.sendLaunch(){
//       viewModelScope.launch {
//           _actions.value = this@sendLaunch
//       }
//    }
//}
//
//
//
//
