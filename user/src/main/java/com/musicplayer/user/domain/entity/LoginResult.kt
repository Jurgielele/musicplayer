package com.musicplayer.user.domain.entity

sealed class LoginResult {
    data object Success : LoginResult()
    data class Error(val errorType: LoginErrorType) : LoginResult()
}