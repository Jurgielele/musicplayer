package com.musicplayer.user.domain.entity

enum class RegisterErrorType {
    INVALID_EMAIL,
    EMAIL_ALREADY_IN_USE,
    WEAK_PASSWORD,
    GENERIC_ERROR
}