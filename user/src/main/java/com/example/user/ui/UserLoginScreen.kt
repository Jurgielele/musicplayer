package com.example.user.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.user.presentation.UserLoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserLoginScreen(
) {
    val viewModel = koinViewModel<UserLoginViewModel>()
    UserLoginScreenContent(){
        viewModel.login()
    }
}

@Composable
private fun UserLoginScreenContent(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Text(text = "User module!")

        Button(
            onClick = { onClick() }
        ) {
            Text(text = "test")
        }
    }
}