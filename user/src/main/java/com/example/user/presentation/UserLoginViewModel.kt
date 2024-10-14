package com.example.user.presentation

import android.util.Log
import com.example.core.android.viewmodel.BaseCommand
import com.example.core.viewmodel.BaseViewModel
import com.example.core.android.viewmodel.CommandType

class UserLoginViewModel: BaseViewModel<UserLoginViewModel.Command, UserLoginViewModel.Action>() {
    sealed class Command(override val type: CommandType = CommandType.Throttable) : BaseCommand {
        data object LoadData : Command()
    }

    sealed class Action {
        data object PrintLog : Action()
    }

    override fun Command.handle(){
        when(this){
            is Command.LoadData -> {
                Log.e("UserLoginViewModel", "command handle load data")
            }
        }
    }
}