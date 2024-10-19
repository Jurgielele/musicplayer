package com.musicplayer.user.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseRepository(
    private val firebaseAuth: FirebaseAuth
) {
    suspend fun register(email: String, password: String): Result {
        return runCatching {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Log.i("FirebaseRepository", "User registered successfully")
            Result.Success
        }.getOrElse { exception ->
            Log.e("FirebaseRepository", "Error registering user", exception)
            Result.Error(exception)
        }
    }

    sealed class Result {
        data object Success : Result()
        data class Error(val exception: Throwable) : Result()
    }
}


