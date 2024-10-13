package com.example.user.presentation

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val presentationModule = module {
    viewModelOf(::UserLoginViewModel)
}