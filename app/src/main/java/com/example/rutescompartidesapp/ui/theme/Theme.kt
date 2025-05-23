package com.example.rutescompartidesapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
        primary = OrangeRC,
        primaryContainer = MateBlackRC,
        secondary = MateBlackRC,
        secondaryContainer = GrayRC,
        onSecondaryContainer = MateBlackRC,
        onTertiaryContainer = DarkGrayRC,
        onTertiary = DarkerGrayRC,
        tertiary = BlueRC,
        background = MateBlackRC,
        onBackground = Color.White

    )

private val LightColorScheme = lightColorScheme(
        primary = OrangeRC,
        primaryContainer = Color.White,
        secondary = GrayRC,
        secondaryContainer = MateBlackRC,
        onSecondaryContainer = Color.White,
        onTertiaryContainer = Color.White,
        onTertiary = MateBlackRC,
        tertiary = BlueRC,
        background = GrayRC,
        onBackground = Color.Black



    /* Other default colors to override
background = Color(0xFFFFFBFE),
surface = Color(0xFFFFFBFE),
onPrimary = Color.White,
onSecondary = Color.White,
onTertiary = Color.White,
onBackground = Color(0xFF1C1B1F),
onSurface = Color(0xFF1C1B1F),
*/
)

@Composable
fun RutesCompartidesAppTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        // Dynamic color is available on Android 12+
        dynamicColor: Boolean = true,
        content: @Composable () -> Unit
) {
    val colorScheme = when {
        /*
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
         */
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
    )
}