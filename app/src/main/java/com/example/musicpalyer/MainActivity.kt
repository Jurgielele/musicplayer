package com.example.musicpalyer

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.user.ui.UserActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToUserActivity()

    }

    private fun navigateToUserActivity() {
        startActivity(Intent(this, UserActivity::class.java))
    }
}

