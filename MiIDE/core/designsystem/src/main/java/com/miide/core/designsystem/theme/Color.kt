package com.miide.core.designsystem.theme

import androidx.compose.ui.graphics.Color

/**
 * HyperOS 风格调色板。
 * 设计语言：柔和中性底 + 高饱和渐变强调色、大圆角、柔和阴影。
 */
object MiColors {
    // 品牌强调色（渐变用）
    val AccentBlue = Color(0xFF4FC3F7)
    val AccentViolet = Color(0xFF7C4DFF)
    val AccentCyan = Color(0xFF26C6DA)
    val AccentOrange = Color(0xFFFFA726)
    val AccentPink = Color(0xFFEC407A)
    val AccentGreen = Color(0xFF66BB6A)
    val AccentRed = Color(0xFFEF5350)

    // 亮色
    val LightBackground = Color(0xFFF5F6FA)
    val LightSurface = Color(0xFFFFFFFF)
    val LightSurfaceVariant = Color(0xFFF0F1F5)
    val LightPrimary = Color(0xFF1976D2)
    val LightOnPrimary = Color(0xFFFFFFFF)
    val LightOnBackground = Color(0xFF1A1C20)
    val LightOnSurface = Color(0xFF1A1C20)
    val LightOutline = Color(0xFFD8DCE3)
    val LightOutlineVariant = Color(0xFFE8EAF0)

    // 暗色
    val DarkBackground = Color(0xFF0D1220)
    val DarkSurface = Color(0xFF161C2E)
    val DarkSurfaceVariant = Color(0xFF1E2638)
    val DarkPrimary = Color(0xFF6FB7FF)
    val DarkOnPrimary = Color(0xFF06213C)
    val DarkOnBackground = Color(0xFFE6E9F0)
    val DarkOnSurface = Color(0xFFE6E9F0)
    val DarkOutline = Color(0xFF2A3246)
    val DarkOutlineVariant = Color(0xFF232B3D)

    // 语义色
    val Success = Color(0xFF4CAF50)
    val Warning = Color(0xFFFFB300)
    val Error = Color(0xFFE53935)
}
