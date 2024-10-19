package com.musicplayer.user.domain.usecase

import com.google.firebase.auth.FirebaseAuthException
import com.musicplayer.user.data.repository.FirebaseRepository
import com.musicplayer.user.domain.entity.RegisterErrorType
import com.musicplayer.user.domain.entity.RegisterResult

class UserRegisterUseCase(
    private val repository: FirebaseRepository
) {
    suspend fun execute(email: String, password: String): RegisterResult {
        val result = repository.register(email = email, password = password)
        return when (result) {
            is FirebaseRepository.Result.Success -> RegisterResult.Success
            is FirebaseRepository.Result.Error -> RegisterResult.Error(result.exception.toErrorType())
        }
    }

    private fun Throwable.toErrorType(): RegisterErrorType {
        return when (this) {
            is FirebaseAuthException -> when (this.errorCode) {
                INVALID_EMAIL_CODE -> RegisterErrorType.INVALID_EMAIL
                EMAIL_ALREADY_IN_USE_CODE -> RegisterErrorType.EMAIL_ALREADY_IN_USE
                WEAK_PASSWORD_CODE -> RegisterErrorType.WEAK_PASSWORD
                else -> RegisterErrorType.GENERIC_ERROR
            }
            else -> RegisterErrorType.GENERIC_ERROR
        }
    }

    private companion object {
        const val INVALID_EMAIL_CODE = "ERROR_INVALID_EMAIL"
        const val EMAIL_ALREADY_IN_USE_CODE = "ERROR_EMAIL_ALREADY_IN_USE"
        const val WEAK_PASSWORD_CODE = "ERROR_WEAK_PASSWORD"
    }
}