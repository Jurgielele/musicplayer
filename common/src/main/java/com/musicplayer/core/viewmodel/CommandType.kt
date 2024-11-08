package com.musicplayer.core.android.viewmodel

interface BaseCommand {
    val type: CommandType
}

enum class CommandType {
    Throttable,
    Immediate
}