package com.skyauto.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// ---------------------------------------------------------------
// HyperOS 3.0 —— 大圆角、柔和层次、通透的表面
// ---------------------------------------------------------------
private val LightColors = lightColorScheme(
    primary = HyperBlue,
    onPrimary = Color.White,
    primaryContainer = HyperBlueSoft,
    onPrimaryContainer = HyperBlueDeep,
    secondary = HyperLavender,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFE4DFFF),
    onSecondaryContainer = Color(0xFF4A3F9E),
    tertiary = HyperCyan,
    onTertiary = Color(0xFF214A56),
    tertiaryContainer = Color(0xFFD6F2F8),
    onTertiaryContainer = Color(0xFF1E4C58),
    background = FrostWhite,
    onBackground = InkGray,
    surface = Color.White,
    onSurface = InkGray,
    surfaceVariant = MistGray,
    onSurfaceVariant = SlateGray,
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFF3F6FC),
    surfaceContainer = Color(0xFFEDF1F8),
    surfaceContainerHigh = Color(0xFFE6EAF4),
    surfaceContainerHighest = Color(0xFFDEE3EF),
    outline = HazeGray,
    outlineVariant = Color(0xFFE2E7F0),
    error = CoralRed,
    onError = Color.White,
    errorContainer = Color(0xFFFFDEE0),
    onErrorContainer = Color(0xFF7A2E2E)
)

private val DarkColors = darkColorScheme(
    primary = HyperBlueSoft,
    onPrimary = Color(0xFF16318F),
    primaryContainer = Color(0xFF3A4FB0),
    onPrimaryContainer = Color(0xFFDCE3FF),
    secondary = HyperLavender,
    onSecondary = Color(0xFF3A2F8C),
    secondaryContainer = Color(0xFF5243A8),
    onSecondaryContainer = Color(0xFFE7E1FF),
    tertiary = HyperCyan,
    onTertiary = Color(0xFF0E4050),
    tertiaryContainer = Color(0xFF2B5A6A),
    onTertiaryContainer = Color(0xFFD0F0F8),
    background = DarkBg,
    onBackground = DarkText,
    surface = DarkSurface,
    onSurface = DarkText,
    surfaceVariant = DarkSurfaceHigh,
    onSurfaceVariant = DarkTextDim,
    surfaceContainerLowest = Color(0xFF10121A),
    surfaceContainerLow = DarkSurface,
    surfaceContainer = DarkSurfaceHigh,
    surfaceContainerHigh = Color(0xFF2E3342),
    surfaceContainerHighest = Color(0xFF383E50),
    outline = Color(0xFF3A4152),
    outlineVariant = Color(0xFF2A2F3E),
    error = Color(0xFFFF8A8A),
    onError = Color(0xFF5C1B1B),
    errorContainer = Color(0xFF8A3434),
    onErrorContainer = Color(0xFFFFDADB)
)

// HyperOS 大圆角体系
private val HyperShapes = Shapes(
    extraSmall = RoundedCornerShape(10.dp),
    small = RoundedCornerShape(14.dp),
    medium = RoundedCornerShape(18.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun SkyAutoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colorScheme,
        typography = HyperOSTypography,
        shapes = HyperShapes,
        content = content
    )
}