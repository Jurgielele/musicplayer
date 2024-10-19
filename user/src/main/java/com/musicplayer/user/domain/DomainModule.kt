package com.musicplayer.user.domain

import org.koin.dsl.module

val domainModule = module {
    single { UserGetLoginDataUseCase() }
    single { UserRegisterUseCase(repository = get()) }
}