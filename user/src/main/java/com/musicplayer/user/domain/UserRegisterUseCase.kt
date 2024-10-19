package com.musicplayer.user.domain

import com.google.firebase.auth.FirebaseAuthException
import com.musicplayer.user.data.repository.FirebaseRepository

class UserRegisterUseCase(
    private val repository: FirebaseRepository
) {
    suspend fun execute(email: String, password: String): RegisterResult {
        val result = repository.register(email = email, password = password)
        return when (result) {
            is FirebaseRepository.Result.Success -> RegisterResult.Success
            is FirebaseRepository.Result.Error -> RegisterResult.Error(result.exception.toMessage())
        }
    }

    private fun Throwable.toMessage(): String {
        return when (this) {
            is FirebaseAuthException -> when (this.errorCode) {
                INVALID_EMAIL_CODE -> INVALID_EMAIL_MESSAGE
                EMAIL_ALREADY_IN_USE_CODE -> EMAIL_ALREADY_IN_USE_MESSAGE
                WEAK_PASSWORD_CODE -> WEAK_PASSWORD_MESSAGE
                else -> GENERIC_ERROR_MESSAGE
            }

            else -> GENERIC_ERROR_MESSAGE
        }
    }
    //TODO: Check error codes if all of them are correct - 19.10.2024
    private companion object {
        const val INVALID_EMAIL_CODE = "ERROR_INVALID_EMAIL"
        const val EMAIL_ALREADY_IN_USE_CODE = "ERROR_EMAIL_ALREADY_IN_USE"
        const val WEAK_PASSWORD_CODE = "ERROR_WEAK_PASSWORD"

        const val GENERIC_ERROR_MESSAGE = "Something went wrong, please try again later."
        const val INVALID_EMAIL_MESSAGE = "The email address is badly formatted."
        const val EMAIL_ALREADY_IN_USE_MESSAGE = "The email address is already in use by another account."
        const val WEAK_PASSWORD_MESSAGE = "The password is too weak."
    }
}

sealed class RegisterResult {
    data object Success : RegisterResult()
    data class Error(val message: String) : RegisterResult()
}