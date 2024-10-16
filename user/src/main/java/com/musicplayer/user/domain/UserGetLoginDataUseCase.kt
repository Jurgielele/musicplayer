package com.musicplayer.user.domain

class UserGetLoginDataUseCase {

    fun execute() = LoginScreenData()

    data class LoginScreenData(
        val title: String = "Log in to Music Player!",
        val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        val loginCtaTitle: String = "Log in",
        val signUpCtaTitle: String = "Create an account",
        val continueAsGuestCtaTitle: String = "Continue as guest",
        val separator: String = "or",
        val forgotPassword: String = "Forgot password?"
    )
}