package com.nikolaswidad.oasenews.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BlackPanther,
    primaryVariant = EerieBlack,
    secondary = White
)

private val LightColorPalette = lightColors(
    primary = LightHouse,
    primaryVariant = StoneWallGrey,
    secondary = Black
)

@Composable
fun NewsAppComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}