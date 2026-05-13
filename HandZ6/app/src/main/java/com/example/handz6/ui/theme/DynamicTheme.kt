package com.example.handz6.ui.theme

// DynamicTheme.kt
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

fun Palette.toColorScheme(darkTheme: Boolean): ColorScheme {
    val primary = Color(getDominantColor(0xFF6750A4.toInt()))
    val secondary = Color(getMutedColor(0xFF625B71.toInt()))
    val tertiary = Color(getLightVibrantColor(0xFF7D5260.toInt()))
    val background = if (darkTheme)
        Color(getDarkMutedColor(0xFF1C1B1F.toInt()))
    else
        Color(getLightMutedColor(0xFFFFFBFE.toInt()))

    return if (darkTheme) {
        darkColorScheme(
            primary = primary,
            secondary = secondary,
            tertiary = tertiary,
            background = background
        )
    } else {
        lightColorScheme(
            primary = primary,
            secondary = secondary,
            tertiary = tertiary,
            background = background
        )
    }
}