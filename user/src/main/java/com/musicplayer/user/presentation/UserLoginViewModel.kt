package com.musicplayer.user.presentation

import android.util.Log
import com.musicplayer.core.android.viewmodel.BaseCommand
import com.musicplayer.core.viewmodel.BaseViewModel
import com.musicplayer.core.android.viewmodel.CommandType
import com.musicplayer.user.domain.RegisterResult
import com.musicplayer.user.domain.UserGetLoginDataUseCase
import com.musicplayer.user.domain.UserGetLoginDataUseCase.LoginScreenData
import com.musicplayer.user.domain.UserLoginUseCase
import com.musicplayer.user.domain.UserRegisterUseCase
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

    init {
        val data = getLoginDataUseCase.execute()
        state.update { state -> state.copy(data = data) }
//        register()
        login()
    }

    override fun Command.handle() {
        when (this) {
            is Command.OnLoginCtaClicked -> TODO()
            is Command.OnSignUpCtaClicked -> TODO()
            Command.OnContinueAsGuestCtaClicked -> TODO()
        }
    }

    private fun login() {
        viewModelScope.launch {
            val result =
                userLoginUseCase.execute(email = "email@email.com", password = "D1!passwordX")
            when (result) {
                is UserLoginUseCase.LoginResult.Success -> {
                    Log.i("UserLoginViewModel", "User logged in successfully")
                }

                is UserLoginUseCase.LoginResult.Error -> {
                    Log.e("UserLoginViewModel", "qaz ${result.message}")
                }
            }
        }
    }


    private fun register() {
        viewModelScope.launch {
            val result =
                userRegisterUseCase.execute(email = "email@email.com", password = "D1!password")
            when (result) {
                is RegisterResult.Success -> {
                    Log.i("UserLoginViewModel", "User registered successfully")
                }

                is RegisterResult.Error -> {
                    Log.e("UserLoginViewModel", "qaz ${result.message}")
                }
            }
        }
    }

    sealed class Action {
        data object PrintLog : Action()
    }

    sealed class Command(override val type: CommandType = CommandType.Throttable) : BaseCommand {
        data object OnLoginCtaClicked : Command()
        data object OnSignUpCtaClicked : Command()
        data object OnContinueAsGuestCtaClicked : Command()
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

