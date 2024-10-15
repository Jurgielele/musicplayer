package com.example.user.presentation

import android.util.Log
import com.example.core.android.viewmodel.BaseCommand
import com.example.core.viewmodel.BaseViewModel
import com.example.core.android.viewmodel.CommandType

class UserLoginViewModel : BaseViewModel<UserLoginViewModel.Command, UserLoginViewModel.Action>() {

    override fun Command.handle() {
        when (this) {
            is Command.onPrimaryCtaClicked -> {
                Log.d("qaz", "onPrimaryCtaClicked")
            }
            is Command.onSecondaryCtaClicked -> {
                Log.d("qaz", "onSecondaryCtaClicked")
            }
        }
    }

    sealed class Action {
        data object PrintLog : Action()
    }

    sealed class Command(override val type: CommandType = CommandType.Throttable) : BaseCommand {
        data object onPrimaryCtaClicked : Command()
        data object onSecondaryCtaClicked : Command()
    }


}