package com.musicplayer.user.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.musicplayer.designsystem.components.DsButton
import com.musicplayer.designsystem.components.DsTextField
import com.musicplayer.user.presentation.UserLoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserLoginScreen(
) {
    val viewModel = koinViewModel<UserLoginViewModel>()
    val viewState = viewModel.viewState.collectAsState().value
    val userEmail = viewModel.userEmail
    val userPassword = viewModel.userPassword
    LaunchedEffect(Unit) {
        viewModel.actions.collect { action -> action.handle() }
    }
    UserLoginScreenContent(
        userEmail = userEmail,
        userPassword = userPassword,
        viewState = viewState,
        trigger = { command -> viewModel.trigger(command) })
}

private fun UserLoginViewModel.Action.handle() = when (this) {
    is UserLoginViewModel.Action.PrintLog -> {
        println("qaz printing log from action")
    }
}


@Composable
private fun OrDivider(text: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = text)
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun UserLoginScreenContent(
    userEmail: String,
    userPassword: String,
    viewState: UserLoginViewModel.ViewState,
    trigger: (UserLoginViewModel.Command) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(all = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        TitleText(viewState.title)
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionText(viewState.description)
        Spacer(modifier = Modifier.height(32.dp))
        EmailAndPasswordFields(
            userEmail = userEmail,
            userPassword = userPassword,
            trigger = trigger
        )
        Spacer(modifier = Modifier.height(4.dp))
        ForgotPasswordText(viewState.forgotPassword)
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton(viewState.loginCtaTitle, trigger)
        Spacer(modifier = Modifier.weight(1f))
        OrDivider(text = viewState.separator)
        SocialButtons()
        SignUpText(viewState.signUpCtaTitle)
    }
}

@Composable
private fun TitleText(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.displaySmall,
        color = Color.Black,
        fontWeight = FontWeight.Black
    )
}

@Composable
private fun DescriptionText(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun ForgotPasswordText(forgotPassword: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = forgotPassword,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.End
    )
}

@Composable
private fun LoginButton(loginCtaTitle: String, trigger: (UserLoginViewModel.Command) -> Unit) {
    DsButton.Primary(
        modifier = Modifier.fillMaxWidth(),
        text = loginCtaTitle,
        buttonSize = DsButton.ButtonSize.Medium
    ) {
        trigger(UserLoginViewModel.Command.OnLoginCtaClicked)
    }
}

@Composable
private fun SignUpText(signUpCtaTitle: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = signUpCtaTitle
    )
}

@Composable
private fun EmailAndPasswordFields(
    userEmail: String,
    userPassword: String,
    trigger: (UserLoginViewModel.Command) -> Unit
) {
    DsTextField.Email(
        value = userEmail,
        label = "Email"
    ) { email ->
        trigger(UserLoginViewModel.Command.OnEmailChanged(email = email))
    }
    Spacer(modifier = Modifier.height(16.dp))
    DsTextField.Password(
        value = userPassword,
        label = "Password"
    ) { password ->
        trigger(UserLoginViewModel.Command.OnPasswordChanged(password = password))
    }
}

//TODO add login by social media - 20.10.2024
@Composable
private fun SocialButtons() {
    Column(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DsButton.Secondary(
            modifier = Modifier.fillMaxWidth(),
            text = "Continue with Google",
            buttonSize = DsButton.ButtonSize.Small
        ) {

        }
        DsButton.Secondary(
            modifier = Modifier.fillMaxWidth(),
            text = "Continue with Facebook",
            buttonSize = DsButton.ButtonSize.Small
        ) {

        }
        DsButton.Secondary(
            modifier = Modifier.fillMaxWidth(),
            text = "Continue with X",
            buttonSize = DsButton.ButtonSize.Small
        ) {

        }
    }
}


@Preview
@Composable
private fun UserLoginScreenContentPreview() {
    UserLoginScreenContent(
        viewState = UserLoginViewModel.ViewState(
            title = "Login",
            description = "Please sign in to continue",
            loginCtaTitle = "Login",
            signUpCtaTitle = "Don't have an account? Sign up here",
            continueAsGuestCtaTitle = "Continue as guest",
            separator = "or",
            forgotPassword = "Forgot password?",


            ),
        userEmail = "patrykjurgielewicz123@gmail.com",
        userPassword = "",
        trigger = {})
}