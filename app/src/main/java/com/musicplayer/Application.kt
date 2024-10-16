package com.musicplayer

import android.app.Application
import com.musicplayer.user.userFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.androix.startup.KoinStartup

class Application : Application() {

    init {
        KoinStartup.onKoinStartup {
            androidContext(this@Application)
            modules(
                userFeatureModule
            )
        }
    }
}