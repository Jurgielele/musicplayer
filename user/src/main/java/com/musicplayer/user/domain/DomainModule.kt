package com.musicplayer.user.domain

import org.koin.dsl.module

val domainModule = module {
    single { UserGetLoginDataUseCase() }
    single { UserLoginUseCase(repository = get()) }
    single { UserRegisterUseCase(repository = get()) }
}