package com.musicplayer.user.domain.entity

enum class LoginErrorType {
    INVALID_EMAIL,
    USER_NOT_FOUND,
    WRONG_PASSWORD,
    INVALID_CREDENTIALS,
    GENERIC_ERROR
}