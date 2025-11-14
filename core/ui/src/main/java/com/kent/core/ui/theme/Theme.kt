package com.kent.core.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Kent Azure Light Color Scheme
 */
private val LightColorScheme = lightColorScheme(
    primary = KentPrimary,
    onPrimary = KentOnPrimary,
    primaryContainer = KentPrimaryContainer,
    onPrimaryContainer = KentOnPrimaryContainer,
    secondary = KentSecondary,
    onSecondary = KentOnSecondary,
    secondaryContainer = KentSecondaryContainer,
    onSecondaryContainer = KentOnSecondaryContainer,
    tertiary = KentTertiary,
    onTertiary = KentOnTertiary,
    tertiaryContainer = KentTertiaryContainer,
    background = KentBackgroundLight,
    onBackground = Color(0xFF0F172A),
    surface = KentSurfaceLight,
    onSurface = Color(0xFF0F172A),
    surfaceVariant = KentSurfaceVariantLight,
    onSurfaceVariant = Color(0xFF475569),
    outline = KentOutline,
    error = KentError,
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFEE2E2),
    onErrorContainer = Color(0xFF991B1B)
)

/**
 * Kent Azure Dark Color Scheme
 */
private val DarkColorScheme = darkColorScheme(
    primary = KentPrimary,
    onPrimary = KentOnPrimary,
    primaryContainer = KentPrimaryContainer,
    onPrimaryContainer = KentOnPrimaryContainer,
    secondary = KentSecondary,
    onSecondary = KentOnSecondary,
    secondaryContainer = KentSecondaryContainer,
    onSecondaryContainer = KentOnSecondaryContainer,
    tertiary = KentTertiary,
    onTertiary = KentOnTertiary,
    tertiaryContainer = KentTertiaryContainer,
    background = KentBackgroundDark,
    onBackground = Color(0xFFF1F5F9),
    surface = KentSurfaceDark,
    onSurface = Color(0xFFF1F5F9),
    surfaceVariant = KentSurfaceVariantDark,
    onSurfaceVariant = Color(0xFFCBD5E1),
    outline = KentOutline,
    error = KentError,
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFF7F1D1D),
    onErrorContainer = Color(0xFFFEE2E2)
)

@Composable
fun KentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, // Android 12+ Dynamic Color
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Apply brand colors to key elements (CTA, NavBar, FAB) even with Dynamic Color
    val brandColorScheme = if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        // Override key elements with brand colors
        colorScheme.copy(
            primary = KentPrimary,
            secondary = KentSecondary,
            tertiary = KentTertiary
        )
    } else {
        colorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = brandColorScheme,
        typography = KentTypography,
        content = content
    )
}

