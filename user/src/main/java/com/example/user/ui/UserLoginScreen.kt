package com.example.user.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.designsystem.DsButton
import com.example.user.presentation.UserLoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserLoginScreen(
) {
    val viewModel = koinViewModel<UserLoginViewModel>()
    LaunchedEffect(Unit) {
        viewModel.actions.collect { action -> action.handle() }
    }
    UserLoginScreenContent(trigger = { command -> viewModel.trigger(command) })
}

private fun UserLoginViewModel.Action.handle() = when (this) {
    is UserLoginViewModel.Action.PrintLog -> {
        println("qaz printing log from action")
    }
}

@Composable
private fun UserLoginScreenContent(
    trigger: (UserLoginViewModel.Command) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Icon(
            modifier = Modifier.fillMaxWidth().size(96.dp),
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Logo",
            tint = MaterialTheme.colorScheme.primary
        )
        Text(modifier = Modifier.fillMaxWidth(), text = "Log in to Music Player", style = MaterialTheme.typography.headlineLarge, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        //text fields
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Forgot password?",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End
        )
        Spacer(modifier = Modifier.height(16.dp))
        DsButton.Primary(
            text = "Log in",
            modifier = Modifier.fillMaxWidth(),
            buttonSize = DsButton.ButtonSize.Medium
        ) { }

        Spacer(modifier = Modifier.weight(1f))
        Buttons(
            primaryCtaTitle = "Create an account",
            secondaryCtaTitle = "Continue as guest",
            onPrimaryCtaClicked = { trigger(UserLoginViewModel.Command.onPrimaryCtaClicked) },
            onSecondaryCtaClicked = { trigger(UserLoginViewModel.Command.onSecondaryCtaClicked) }
        )
    }
}
@Composable
private fun Buttons(
    primaryCtaTitle: String,
    secondaryCtaTitle: String,
    onPrimaryCtaClicked: () -> Unit,
    onSecondaryCtaClicked: () -> Unit
){
    DsButton.Primary(
        text = primaryCtaTitle,
        modifier = Modifier.fillMaxWidth(),
        buttonSize = DsButton.ButtonSize.Medium
    ) {
        onPrimaryCtaClicked()
    }
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "OR")
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
    DsButton.Secondary(
        text = secondaryCtaTitle,
        modifier = Modifier.fillMaxWidth(),
        buttonSize = DsButton.ButtonSize.Medium
    ) {
        onSecondaryCtaClicked()
    }

}
@Composable
private fun OrDivider(){
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        HorizontalDivider(modifier = Modifier.weight(1f))
        Text(modifier = Modifier.padding(horizontal = 16.dp), text = "OR")
        HorizontalDivider(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun UserLoginScreenContentPreview() {
    UserLoginScreenContent(trigger = {})
}