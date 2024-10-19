package com.musicplayer.user.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.musicplayer.user.data.repository.FirebaseRepository
import org.koin.dsl.module


internal val dataModule = module {
    single { Firebase.auth }
    single { FirebaseRepository(firebaseAuth = get()) }
}