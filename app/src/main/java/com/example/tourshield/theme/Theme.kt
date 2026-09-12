package com.example.tourshield.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val TourShieldDarkColorScheme = darkColorScheme(
    primary = TealPrimary,
    onPrimary = TealOnPrimary,
    primaryContainer = TealContainer,
    onPrimaryContainer = TealOnContainer,
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = CardSurface,
    onSurface = TextPrimary,
    surfaceVariant = SosDarkTeal,
    onSurfaceVariant = TextSubdued,
    outline = DividerColor,
    outlineVariant = DividerColor,
)

@Composable
fun TourShieldTheme(
    // Always force dark – the spec says dark charcoal / near-black theme
    darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    // We intentionally skip dynamic color extraction here so the precise
    // brand palette (#0d4757, #252a30, etc.) is always used, matching the
    // Material You "custom seed" dark-theme pattern described in the spec.
    MaterialTheme(
        colorScheme = TourShieldDarkColorScheme,
        typography = Typography,
        content = content,
    )
}
