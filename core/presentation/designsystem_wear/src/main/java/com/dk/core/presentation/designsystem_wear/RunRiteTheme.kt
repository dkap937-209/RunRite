package com.dk.core.presentation.designsystem_wear

import androidx.compose.runtime.Composable
import androidx.wear.compose.material3.ColorScheme
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Typography
import com.dk.core.presentation.designsystem.DarkColourScheme
import com.dk.core.presentation.designsystem.Poppins

private fun createColourScheme(): ColorScheme {
    val phoneTheme = DarkColourScheme
    return ColorScheme(
        primary = phoneTheme.primary,
        primaryContainer = phoneTheme.primaryContainer,
        onPrimary = phoneTheme.onPrimary,
        onPrimaryContainer = phoneTheme.onPrimaryContainer,
        secondary = phoneTheme.secondary,
        onSecondary = phoneTheme.onSecondary,
        secondaryContainer = phoneTheme.secondaryContainer,
        onSecondaryContainer = phoneTheme.onSecondaryContainer,
        tertiary = phoneTheme.tertiary,
        onTertiary = phoneTheme.onTertiary,
        tertiaryContainer = phoneTheme.tertiaryContainer,
        onTertiaryContainer = phoneTheme.onTertiaryContainer,
        surfaceContainer = phoneTheme.surface,
        onSurface = phoneTheme.onSurface,
        surfaceContainerLow = phoneTheme.surfaceVariant,
        onSurfaceVariant = phoneTheme.onSurfaceVariant,
        background = phoneTheme.background,
        error = phoneTheme.error,
        onError = phoneTheme.onError,
        onBackground = phoneTheme.onBackground
    )
}

private fun createTypography(): Typography {
    return Typography(
        defaultFontFamily = Poppins
    )
}

private val WearColours = createColourScheme()
private val WearTypography = createTypography()

@Composable
fun RunRiteTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = WearColours,
        typography = WearTypography,
    ) {
        content()
    }
}