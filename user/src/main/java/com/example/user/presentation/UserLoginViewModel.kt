package com.example.user.presentation

import com.example.core.android.viewmodel.BaseCommand
import com.example.core.viewmodel.BaseViewModel
import com.example.core.android.viewmodel.CommandType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UserLoginViewModel : BaseViewModel<UserLoginViewModel.Command, UserLoginViewModel.Action>() {

    private val state = MutableStateFlow(State())
    val viewState: StateFlow<ViewState> = state.mapState { state ->
        state.toViewState()
    }

    init {
        state.update { state -> state.copy(data = LoginMockData()) }
    }

    override fun Command.handle() {
        when (this) {
            is Command.OnLoginCtaClicked -> TODO()
            is Command.OnSignUpCtaClicked -> TODO()
            Command.OnContinueAsGuestCtaClicked -> TODO()
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
        val data: LoginMockData? = null
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

private data class LoginMockData(
    val title: String = "Log in to Music Player!",
    val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
    val loginCtaTitle: String = "Log in",
    val signUpCtaTitle: String = "Create an account",
    val continueAsGuestCtaTitle: String = "Continue as guest",
    val separator: String = "or",
    val forgotPassword: String = "Forgot password?"
)