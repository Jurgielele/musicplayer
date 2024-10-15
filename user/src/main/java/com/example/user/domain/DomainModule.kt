package com.example.user.domain

import org.koin.dsl.module

val domainModule = module {
    single { UserGetLoginDataUseCase() }
}