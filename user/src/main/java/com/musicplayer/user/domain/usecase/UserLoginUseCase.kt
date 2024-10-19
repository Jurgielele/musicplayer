package com.musicplayer.user.domain.usecase

import com.google.firebase.auth.FirebaseAuthException
import com.musicplayer.user.data.repository.FirebaseRepository
import com.musicplayer.user.domain.entity.LoginErrorType
import com.musicplayer.user.domain.entity.LoginResult

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
            is FirebaseRepository.Result.Error -> LoginResult.Error(result.exception.toErrorType())
        }
    }

    private fun Throwable.toErrorType(): LoginErrorType {
        return when (this) {
            is FirebaseAuthException -> when (this.errorCode) {
                INVALID_EMAIL_CODE -> LoginErrorType.INVALID_EMAIL
                USER_NOT_FOUND_CODE -> LoginErrorType.USER_NOT_FOUND
                WRONG_PASSWORD_CODE -> LoginErrorType.WRONG_PASSWORD
                INVALID_CREDENTIALS_CODE -> LoginErrorType.INVALID_CREDENTIALS
                else -> LoginErrorType.GENERIC_ERROR
            }
            else -> LoginErrorType.GENERIC_ERROR
        }
    }

    private companion object {
        const val INVALID_EMAIL_CODE = "ERROR_INVALID_EMAIL"
        const val USER_NOT_FOUND_CODE = "ERROR_USER_NOT_FOUND"
        const val WRONG_PASSWORD_CODE = "ERROR_WRONG_PASSWORD"
        const val INVALID_CREDENTIALS_CODE = "ERROR_INVALID_CREDENTIAL"
    }
}