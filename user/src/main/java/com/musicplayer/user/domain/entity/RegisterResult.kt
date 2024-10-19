package com.musicplayer.user.domain.entity

sealed class RegisterResult {
    data object Success : RegisterResult()
    data class Error(val errorType: RegisterErrorType) : RegisterResult()
}