package com.miide.core.editor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme

/**
 * 支持幽灵文本（ghost text）的编辑器。
 *
 * 幽灵文本：在光标处显示半透明的建议代码，不改动真实文档，按 Tab 接受、Esc 取消。
 * 基于 Rosemoe [CodeEditor]，在 [onDraw] 中利用 [getCharOffsetX]/[getCharOffsetY]、
 * [getRowHeight]、[getTextPaint] 计算绘制坐标与画笔，颜色取当前主题的注释色并降透明度。
 */
class GhostCodeEditor @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CodeEditor(context, attrs, defStyleAttr) {

    @Volatile
    private var ghostText: String? = null

    /** 幽灵文本可见性变化回调（供 Compose 层同步状态）。 */
    var onGhostVisibilityChange: ((Boolean) -> Unit)? = null

    /** 供绘制使用的独立画笔（克隆自正文字体，避免污染共享画笔）。 */
    private val ghostPaint = Paint()

    /** 当前是否显示幽灵文本。 */
    val isGhostVisible: Boolean
        get() = !ghostText.isNullOrEmpty()

    /** 当前幽灵文本内容。 */
    fun currentGhost(): String? = ghostText

    /** 更新幽灵文本（null/空白则隐藏），并请求重绘。 */
    fun setGhostText(text: String?) {
        val newText = text?.takeIf { it.isNotBlank() }
        if (newText == ghostText) return
        ghostText = newText
        postInvalidateOnAnimation()
        onGhostVisibilityChange?.invoke(isGhostVisible)
    }

    /** 清空幽灵文本。 */
    fun clearGhost() = setGhostText(null)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawGhost(canvas)
    }

    private fun drawGhost(canvas: Canvas) {
        val text = ghostText ?: return
        val cursor = getCursor()
        if (cursor.isSelected) return

        val line = cursor.leftLine
        val column = cursor.leftColumn

        // 克隆正文字体设置，仅覆盖颜色，不影响编辑器共享画笔
        ghostPaint.set(getTextPaint())
        ghostPaint.color = getColorScheme().getColor(EditorColorScheme.COMMENT)
        ghostPaint.alpha = GHOST_ALPHA

        val fm = ghostPaint.fontMetrics
        val baselineOffset = -fm.ascent
        val rowHeight = getRowHeight()

        var x = getCharOffsetX(line, column)
        var y = getCharOffsetY(line, column)

        val lines = text.split('\n')
        for ((i, lineText) in lines.withIndex()) {
            if (i > 0) {
                // 多行幽灵从行首继续（左对齐）
                x = 0f
                y += rowHeight
            }
            if (lineText.isEmpty()) continue
            canvas.drawText(lineText, x, y + baselineOffset, ghostPaint)
        }
    }

    private companion object {
        /** 幽灵文本透明度（0-255）。 */
        const val GHOST_ALPHA = 120
    }
}
