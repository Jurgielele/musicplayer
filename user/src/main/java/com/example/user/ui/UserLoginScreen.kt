package com.example.user.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.user.presentation.UserLoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserLoginScreen(
) {
    val viewModel = koinViewModel<UserLoginViewModel>()
    LaunchedEffect(Unit) {
        viewModel.actions.collect { action -> action.handle() }
    }
    UserLoginScreenContent(
        trigger = { command -> viewModel.trigger(command) }
    )
}

private fun UserLoginViewModel.Action.handle() = when(this){
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
    ) {
        Text(text = "User module!")

        Button(
            onClick = { trigger(UserLoginViewModel.Command.LoadData) }
        ) {
            Text(text = "test")
        }
    }
}