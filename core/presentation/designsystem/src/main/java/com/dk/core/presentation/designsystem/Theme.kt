package com.dk.core.presentation.designsystem

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColourScheme = darkColorScheme(
    primary = RunriteGreen,
    background = RunriteBlack,
    surface = RunriteDarkGray,
    secondary = RunriteWhite,
    tertiary = RunriteWhite,
    primaryContainer = RunriteGreen30,
    onPrimary = RunriteBlack,
    onBackground = RunriteWhite,
    onSurface = RunriteWhite,
    onSurfaceVariant = RunriteGray,
    error = RunriteDarkRed,
    errorContainer = RunriteDarkRed5
)

@Composable
fun RunRiteTheme(
    content: @Composable () -> Unit
) {
    val colourScheme = DarkColourScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colourScheme,
        typography = Typography,
        content = content
    )
}