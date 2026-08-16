package com.miide.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = MiColors.LightPrimary,
    onPrimary = MiColors.LightOnPrimary,
    background = MiColors.LightBackground,
    onBackground = MiColors.LightOnBackground,
    surface = MiColors.LightSurface,
    onSurface = MiColors.LightOnSurface,
    surfaceVariant = MiColors.LightSurfaceVariant,
    outline = MiColors.LightOutline,
    outlineVariant = MiColors.LightOutlineVariant,
    secondary = MiColors.AccentViolet,
    tertiary = MiColors.AccentCyan,
    error = MiColors.Error
)

private val DarkColors = darkColorScheme(
    primary = MiColors.DarkPrimary,
    onPrimary = MiColors.DarkOnPrimary,
    background = MiColors.DarkBackground,
    onBackground = MiColors.DarkOnBackground,
    surface = MiColors.DarkSurface,
    onSurface = MiColors.DarkOnSurface,
    surfaceVariant = MiColors.DarkSurfaceVariant,
    outline = MiColors.DarkOutline,
    outlineVariant = MiColors.DarkOutlineVariant,
    secondary = Color(0xFFB39DDB),
    tertiary = Color(0xFF80DEEA),
    error = MiColors.Error
)

/**
 * 主题入口：支持系统深/浅色 + 动态取色（Material You），
 * 底色使用 HyperOS 品牌化调色板。
 */
@Composable
fun MiIdeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S ->
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MiTypography,
        shapes = MiShapes,
        content = content
    )
}
