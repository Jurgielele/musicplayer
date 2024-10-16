package com.musicplayer.user

import com.musicplayer.user.domain.domainModule
import com.musicplayer.user.presentation.presentationModule

val userFeatureModule = listOf(
    presentationModule,
    domainModule
)