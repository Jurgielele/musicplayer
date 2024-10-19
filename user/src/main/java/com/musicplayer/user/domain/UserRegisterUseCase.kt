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
                InvalidEmailCode -> InvalidEmailMessage
                EmailAlreadyInUseCode -> EmailAlreadyInUseMessage
                WeakPasswordCode -> WeakPasswordMessage
                else -> GenericErrorMessage
            }

            else -> GenericErrorMessage
        }
    }

    private companion object {
        const val InvalidEmailCode = "ERROR_INVALID_EMAIL"
        const val EmailAlreadyInUseCode = "ERROR_EMAIL_ALREADY_IN_USE"
        const val WeakPasswordCode = "ERROR_WEAK_PASSWORD"

        const val GenericErrorMessage = "Something went wrong, please try again later."
        const val InvalidEmailMessage = "The email address is badly formatted."
        const val EmailAlreadyInUseMessage =
            "The email address is already in use by another account."
        const val WeakPasswordMessage = "The password is too weak."
    }
}

sealed class RegisterResult {
    data object Success : RegisterResult()
    data class Error(val message: String) : RegisterResult()
}