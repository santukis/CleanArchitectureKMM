package com.santukis.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF03B5DE)
val DarkBlue = Color(0xFF02819E)
val Green = Color(0xFF18CEB2)
val DarkGreen = Color(0xFF14A38D)
val WhiteTransparent = Color(0x80FFFFFF)

@Composable
fun MaterialTheme.statusBarColor(): Color =
    if (isSystemInDarkTheme()) {
        colors.surface

    } else {
        colors.primary
    }

@Composable
fun MaterialTheme.selectedDotColor(): Color =
    if (isSystemInDarkTheme()) {
        colors.secondary

    } else {
        colors.primary
    }

@Composable
fun MaterialTheme.unselectedDotColor(): Color =
    if (isSystemInDarkTheme()) {
        colors.onSurface.copy(alpha = 0.5f)

    } else {
        colors.primaryVariant.copy(alpha = 0.5f)
    }