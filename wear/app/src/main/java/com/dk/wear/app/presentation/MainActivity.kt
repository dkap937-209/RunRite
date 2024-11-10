package com.dk.wear.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dk.core.presentation.designsystem_wear.RunRiteTheme
import com.dk.wear.run.presentation.TrackerScreenScreenRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            RunRiteTheme {
                TrackerScreenScreenRoot()
            }
        }
    }
}