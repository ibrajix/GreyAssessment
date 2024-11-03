package com.ibrajix.greyassessment.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorPalette = darkColors(
    primary = GreyBlack,
    primaryVariant = Color.DarkGray,
    secondary = GreyWhite,
    background = GreyBlack,
    surface = GreyBlack
)

private val LightColorPalette = lightColors(
    primary = GreyBlack,
    primaryVariant = Color.LightGray,
    secondary = GreyWhite,
    background = GreyWhite,
    surface = GreyWhite
)

@Composable
fun GreyAssessmentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) LightColorPalette else LightColorPalette //for now, use light mode

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content
    )
}
