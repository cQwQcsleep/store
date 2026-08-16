package com.miide.core.editor.theme

import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel

/**
 * HyperOS 风格编辑器主题：框体与 token 颜色统一。
 *
 * 继承 [TextMateColorScheme] 实现「框体与 token 融合」：
 *
 * - **框体**（背景 / 行号 / 选区 / 滚动条 / 补全窗等）：先由 TextMate 主题的
 *   `colors` 段读出默认值，再叠加本类的 HyperOS 框体调色板（与
 *   `assets/textmate/miide-*.json` 保持一致，代码内为最终权威值）；
 * - **token**：完全由 TextMate 主题的 `tokenColors` 段按 scope 提供，
 *   经 [TextMateColorScheme.getColor] 自动映射到各 token 色槽
 *   （因此本类不再手工设置 token 颜色，避免遮蔽主题高亮）。
 *
 * 由 [ThemeManager] 从 assets 加载 [ThemeModel] 后创建，主题切换（深浅色）
 * 通过 [ThemeRegistry] 广播到所有已注册编辑器。
 */
class MiEditorTheme(
    themeRegistry: ThemeRegistry,
    themeModel: ThemeModel,
) : TextMateColorScheme(themeRegistry, themeModel) {

    init {
        // 确保主题模型已加载；setTheme() 会清空颜色表并触发
        // applyDefault()（动态派发到本类实现，应用框体调色板）。
        runCatching { if (!themeModel.isLoaded()) themeModel.load() }
        setTheme(themeModel)
    }

    override fun applyDefault() {
        super.applyDefault()                       // TextMate 主题 -> 框体默认色
        if (isDark()) applyDarkFrame() else applyLightFrame()  // HyperOS 框体调色板
    }

    private fun applyDarkFrame() {
        // 底色与正文
        setColor(WHOLE_BACKGROUND, 0xFF0D1220)          // MiColors.DarkBackground
        setColor(TEXT_NORMAL, 0xFFE6E9F0)               // MiColors.DarkOnBackground
        setColor(CURRENT_LINE, 0xFF161C2E)              // MiColors.DarkSurface
        setColor(SELECTED_TEXT_BACKGROUND, 0x336FB7FF)
        setColor(SELECTION_INSERT, 0xFF6FB7FF)
        setColor(SELECTION_HANDLE, 0xFF6FB7FF)
        setColor(TEXT_SELECTED, 0xFFE6E9F0)

        // 行号区
        setColor(LINE_NUMBER_BACKGROUND, 0xFF0D1220)
        setColor(LINE_NUMBER_PANEL, 0xFF0D1220)
        setColor(LINE_NUMBER, 0xFF5A6478)
        setColor(LINE_NUMBER_CURRENT, 0xFF6FB7FF)
        setColor(LINE_NUMBER_PANEL_TEXT, 0xFF6FB7FF)
        setColor(LINE_DIVIDER, 0xFF2A3246)

        // 滚动条
        setColor(SCROLL_BAR_THUMB, 0x666FB7FF)
        setColor(SCROLL_BAR_THUMB_PRESSED, 0x996FB7FF)
        setColor(SCROLL_BAR_TRACK, 0x00000000)

        // 补全窗口
        setColor(COMPLETION_WND_BACKGROUND, 0xFF161C2E)
        setColor(COMPLETION_WND_CORNER, 0xFF161C2E)
        setColor(COMPLETION_WND_TEXT_PRIMARY, 0xFFE6E9F0)
        setColor(COMPLETION_WND_TEXT_SECONDARY, 0xFF8A94A8)
        setColor(COMPLETION_WND_ITEM_CURRENT, 0x336FB7FF)

        // 诊断 / 提示
        setColor(PROBLEM_ERROR, 0xFFEF5350)
        setColor(PROBLEM_WARNING, 0xFFFFB300)
        setColor(PROBLEM_TYPO, 0xFFFFB300)
        setColor(UNDERLINE, 0xFF7C4DFF)
        setColor(MATCHED_TEXT_BACKGROUND, 0x33FFB300)
        setColor(HIGHLIGHTED_DELIMITERS_FOREGROUND, 0xFF89DDFF)
        setColor(HIGHLIGHTED_DELIMITERS_BACKGROUND, 0x337C4DFF)
        setColor(HIGHLIGHTED_DELIMITERS_UNDERLINE, 0xFF7C4DFF)
        setColor(STRIKE_THROUGH, 0xFFEF5350)
        setColor(STRIKETHROUGH, 0xFFEF5350)

        // 折叠块 / 结构
        setColor(BLOCK_LINE, 0xFF2A3246)
        setColor(BLOCK_LINE_CURRENT, 0xFF6FB7FF)
        setColor(SIDE_BLOCK_LINE, 0xFF2A3246)
        setColor(LINE_BLOCK_LABEL, 0xFF8A94A8)
        setColor(NON_PRINTABLE_CHAR, 0xFF5A6478)

        // 内联提示 / 片段
        setColor(TEXT_INLAY_HINT_FOREGROUND, 0xFF8A94A8)
        setColor(TEXT_INLAY_HINT_BACKGROUND, 0x1A8A94A8)
        setColor(SNIPPET_BACKGROUND_EDITING, 0x335266E8)
        setColor(SNIPPET_BACKGROUND_RELATED, 0x335266E8)
        setColor(SNIPPET_BACKGROUND_INACTIVE, 0x332A3246)

        // 签名提示 / 诊断气泡
        setColor(SIGNATURE_BACKGROUND, 0xFF161C2E)
        setColor(SIGNATURE_TEXT_NORMAL, 0xFFE6E9F0)
        setColor(SIGNATURE_TEXT_HIGHLIGHTED_PARAMETER, 0xFF6FB7FF)
        setColor(DIAGNOSTIC_TOOLTIP_BACKGROUND, 0xFF1E2638)
        setColor(DIAGNOSTIC_TOOLTIP_BRIEF_MSG, 0xFFE6E9F0)
        setColor(DIAGNOSTIC_TOOLTIP_DETAILED_MSG, 0xFF8A94A8)
        setColor(DIAGNOSTIC_TOOLTIP_ACTION, 0xFF6FB7FF)

        // 其它
        setColor(STICKY_SCROLL_DIVIDER, 0xFF2A3246)
        setColor(HARD_WRAP_MARKER, 0xFF5A6478)
        setColor(FUNCTION_CHAR_BACKGROUND_STROKE, 0xFF7C4DFF)
        setColor(STATIC_SPAN_FOREGROUND, 0xFFE6E9F0)
        setColor(STATIC_SPAN_BACKGROUND, 0x00000000)
    }

    private fun applyLightFrame() {
        // 底色与正文
        setColor(WHOLE_BACKGROUND, 0xFFF5F6FA)          // MiColors.LightBackground
        setColor(TEXT_NORMAL, 0xFF1A1C20)               // MiColors.LightOnBackground
        setColor(CURRENT_LINE, 0xFFECEFF5)
        setColor(SELECTED_TEXT_BACKGROUND, 0x331976D2)
        setColor(SELECTION_INSERT, 0xFF1976D2)
        setColor(SELECTION_HANDLE, 0xFF1976D2)
        setColor(TEXT_SELECTED, 0xFF1A1C20)

        // 行号区
        setColor(LINE_NUMBER_BACKGROUND, 0xFFF5F6FA)
        setColor(LINE_NUMBER_PANEL, 0xFFF5F6FA)
        setColor(LINE_NUMBER, 0xFF9AA3B2)
        setColor(LINE_NUMBER_CURRENT, 0xFF1976D2)
        setColor(LINE_NUMBER_PANEL_TEXT, 0xFF1976D2)
        setColor(LINE_DIVIDER, 0xFFD8DCE3)

        // 滚动条
        setColor(SCROLL_BAR_THUMB, 0x661976D2)
        setColor(SCROLL_BAR_THUMB_PRESSED, 0x991976D2)
        setColor(SCROLL_BAR_TRACK, 0x00000000)

        // 补全窗口
        setColor(COMPLETION_WND_BACKGROUND, 0xFFFFFFFF)
        setColor(COMPLETION_WND_CORNER, 0xFFFFFFFF)
        setColor(COMPLETION_WND_TEXT_PRIMARY, 0xFF1A1C20)
        setColor(COMPLETION_WND_TEXT_SECONDARY, 0xFF6B7280)
        setColor(COMPLETION_WND_ITEM_CURRENT, 0x331976D2)

        // 诊断 / 提示
        setColor(PROBLEM_ERROR, 0xFFE53935)
        setColor(PROBLEM_WARNING, 0xFFFF8F00)
        setColor(PROBLEM_TYPO, 0xFFFF8F00)
        setColor(UNDERLINE, 0xFF7C4DFF)
        setColor(MATCHED_TEXT_BACKGROUND, 0x33FFB300)
        setColor(HIGHLIGHTED_DELIMITERS_FOREGROUND, 0xFF00838F)
        setColor(HIGHLIGHTED_DELIMITERS_BACKGROUND, 0x337C4DFF)
        setColor(HIGHLIGHTED_DELIMITERS_UNDERLINE, 0xFF7C4DFF)
        setColor(STRIKE_THROUGH, 0xFFE53935)
        setColor(STRIKETHROUGH, 0xFFE53935)

        // 折叠块 / 结构
        setColor(BLOCK_LINE, 0xFFD8DCE3)
        setColor(BLOCK_LINE_CURRENT, 0xFF1976D2)
        setColor(SIDE_BLOCK_LINE, 0xFFD8DCE3)
        setColor(LINE_BLOCK_LABEL, 0xFF6B7280)
        setColor(NON_PRINTABLE_CHAR, 0xFF9AA3B2)

        // 内联提示 / 片段
        setColor(TEXT_INLAY_HINT_FOREGROUND, 0xFF6B7280)
        setColor(TEXT_INLAY_HINT_BACKGROUND, 0x1A6B7280)
        setColor(SNIPPET_BACKGROUND_EDITING, 0x334F6BED)
        setColor(SNIPPET_BACKGROUND_RELATED, 0x334F6BED)
        setColor(SNIPPET_BACKGROUND_INACTIVE, 0x33D8DCE3)

        // 签名提示 / 诊断气泡
        setColor(SIGNATURE_BACKGROUND, 0xFFFFFFFF)
        setColor(SIGNATURE_TEXT_NORMAL, 0xFF1A1C20)
        setColor(SIGNATURE_TEXT_HIGHLIGHTED_PARAMETER, 0xFF1976D2)
        setColor(DIAGNOSTIC_TOOLTIP_BACKGROUND, 0xFFF0F1F5)
        setColor(DIAGNOSTIC_TOOLTIP_BRIEF_MSG, 0xFF1A1C20)
        setColor(DIAGNOSTIC_TOOLTIP_DETAILED_MSG, 0xFF6B7280)
        setColor(DIAGNOSTIC_TOOLTIP_ACTION, 0xFF1976D2)

        // 其它
        setColor(STICKY_SCROLL_DIVIDER, 0xFFD8DCE3)
        setColor(HARD_WRAP_MARKER, 0xFF9AA3B2)
        setColor(FUNCTION_CHAR_BACKGROUND_STROKE, 0xFF7C4DFF)
        setColor(STATIC_SPAN_FOREGROUND, 0xFF1A1C20)
        setColor(STATIC_SPAN_BACKGROUND, 0x00000000)
    }

    /**
     * 兼容重载：8 位十六进制字面量 `0xFF000000` 在 Kotlin 中是 [Long]，
     * 这里转成 [Int] 后调用父类实现，避免逐处写 `.toInt()`。
     */
    fun setColor(colorId: Int, color: Long) = setColor(colorId, color.toInt())

    companion object {
        /** 编辑器可用的主题变体名。 */
        val Dark = "MiIDE Dark"
        val Light = "MiIDE Light"
    }
}
