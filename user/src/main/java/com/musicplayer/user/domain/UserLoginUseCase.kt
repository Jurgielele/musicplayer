package com.musicplayer.user.domain

import com.google.firebase.auth.FirebaseAuthException
import com.musicplayer.user.data.repository.FirebaseRepository

class UserLoginUseCase(
    private val repository: FirebaseRepository
) {

    suspend fun execute(
        email: String,
        password: String
    ): LoginResult {
        val result = repository.login(email = email, password = password)
        return when (result) {
            is FirebaseRepository.Result.Success -> LoginResult.Success
            is FirebaseRepository.Result.Error -> LoginResult.Error(result.exception.toMessage())
        }
    }

    private fun Throwable.toMessage(): String {
        return when (this) {
            is FirebaseAuthException -> when (this.errorCode) {
                INVALID_EMAIL_CODE -> INVALID_EMAIL_MESSAGE
                USER_NOT_FOUND_CODE -> USER_NOT_FOUND_MESSAGE
                WRONG_PASSWORD_CODE -> WRONG_PASSWORD_MESSAGE
                INVALID_CREDENTIALS_CODE -> INVALID_CREDENTIALS_MESSAGE
                else -> GENERIC_ERROR_MESSAGE
            }
            else -> GENERIC_ERROR_MESSAGE
        }
    }


    //TODO: Check error codes if all of them are correct - 19.10.2024
    private companion object {
        const val INVALID_EMAIL_CODE = "ERROR_INVALID_EMAIL"
        const val USER_NOT_FOUND_CODE = "ERROR_USER_NOT_FOUND"
        const val WRONG_PASSWORD_CODE = "ERROR_WRONG_PASSWORD"
        const val INVALID_CREDENTIALS_CODE = "ERROR_INVALID_CREDENTIAL"

        const val GENERIC_ERROR_MESSAGE = "Something went wrong, please try again later."
        const val INVALID_EMAIL_MESSAGE = "The email address is badly formatted."
        const val USER_NOT_FOUND_MESSAGE = "There is no user record corresponding to this email."
        const val WRONG_PASSWORD_MESSAGE = "The password is invalid."
        const val INVALID_CREDENTIALS_MESSAGE = "The credentials are invalid."
    }

    sealed class LoginResult {
        data object Success : LoginResult()
        data class Error(val message: String) : LoginResult()
    }
}