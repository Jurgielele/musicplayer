package com.musicplayer.user.domain

import com.musicplayer.user.domain.usecase.UserGetLoginDataUseCase
import com.musicplayer.user.domain.usecase.UserLoginUseCase
import com.musicplayer.user.domain.usecase.UserRegisterUseCase
import org.koin.dsl.module

val domainModule = module {
    single { UserGetLoginDataUseCase() }
    single { UserLoginUseCase(repository = get()) }
    single { UserRegisterUseCase(repository = get()) }
}