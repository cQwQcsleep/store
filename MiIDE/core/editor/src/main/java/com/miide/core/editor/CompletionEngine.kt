package com.miide.core.editor

import io.github.rosemoe.sora.widget.CodeEditor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 幽灵补全引擎。
 *
 * 两种触发模式：
 * - 自动触发：输入过程中连续敲出标识符时，防抖后自动展示最佳建议；
 * - 手动触发：[triggerManual] 在任何光标位置展示建议（适合无长前缀场景）。
 *
 * 交互：
 * - 接受：Tab（[accept]），把幽灵文本插入光标处；
 * - 取消：Esc（[dismiss]）；
 * - 切换：连续调用 [cycleNext] 在多个候选中轮换（当前由手动按钮触发）。
 */
class CompletionEngine(
    private val editor: GhostCodeEditor,
    private val source: CompletionSource = DocumentCompletionSource(),
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var debounceJob: Job? = null
    private var computeJob: Job? = null

    private var candidates: List<String> = emptyList()
    private var index: Int = -1

    /** 当前文件扩展名（小写，用于关键字补全）。 */
    var fileExtension: String = ""

    /** 是否展示幽灵文本。 */
    val isGhostActive: Boolean
        get() = editor.isGhostVisible

    /**
     * 内容 / 光标变化回调（编辑器事件触发）：自动触发入口。
     * 连续输入时防抖，减少无谓计算。
     */
    fun onInputChanged() {
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(AUTO_TRIGGER_DELAY_MS)
            refresh(manual = false)
        }
    }

    /** 手动触发：无论前缀长短都尝试展示建议（无前缀时展示高频标识符）。 */
    fun triggerManual() {
        debounceJob?.cancel()
        refresh(manual = true)
    }

    /** 切换到下一个候选；多个候选时循环。返回是否仍在展示。 */
    fun cycleNext(): Boolean {
        if (candidates.isEmpty()) return false
        index = (index + 1) % candidates.size
        editor.setGhostText(candidates[index])
        return true
    }

    /** 接受当前幽灵文本并插入文档。返回是否成功。 */
    fun accept(): Boolean {
        val ghost = editor.currentGhost() ?: return false
        if (ghost.isEmpty()) return false
        editor.commitText(ghost)
        clearState()
        return true
    }

    /** 取消幽灵文本。 */
    fun dismiss() {
        clearState()
        editor.clearGhost()
    }

    private fun refresh(manual: Boolean) {
        val cursor = editor.getCursor()
        val prefix = extractPrefix(cursor.leftLine, cursor.leftColumn)

        if (!manual && prefix.length < AUTO_MIN_PREFIX) {
            // 自动模式下前缀过短则不展示
            clearState()
            editor.clearGhost()
            return
        }

        val context = CompletionContext(
            text = editor.getText().toString(),
            cursorLine = cursor.leftLine,
            cursorColumn = cursor.leftColumn,
            fileExtension = fileExtension,
            prefix = prefix,
        )

        computeJob?.cancel()
        computeJob = scope.launch {
            val suggestions = withContext(Dispatchers.Default) { source.suggestions(context) }
            // 计算期间用户可能继续输入/移动光标，丢弃过期结果（下一次输入防抖会覆盖）
            if (isStale(context)) return@launch
            if (suggestions.isEmpty()) {
                clearState()
                editor.clearGhost()
                return@launch
            }
            candidates = suggestions
            index = 0
            editor.setGhostText(suggestions[0])
        }
    }

    /** 校验用户是否在计算期间改变了光标/前缀。 */
    private fun isStale(context: CompletionContext): Boolean {
        val cursor = editor.getCursor()
        return cursor.leftLine != context.cursorLine ||
            cursor.leftColumn != context.cursorColumn ||
            extractPrefix(cursor.leftLine, cursor.leftColumn) != context.prefix
    }

    /** 提取光标所在行的行首到光标位置末尾的标识符。 */
    private fun extractPrefix(line: Int, column: Int): String {
        val content = editor.getText()
        if (line !in 0 until content.lineCount) return ""
        val lineText = content.getLineString(line)
        val upTo = lineText.take(column.coerceIn(0, lineText.length))
        val match = IDENTIFIER_TAIL.find(upTo)
        return match?.value ?: ""
    }

    private fun clearState() {
        candidates = emptyList()
        index = -1
    }

    private companion object {
        const val AUTO_TRIGGER_DELAY_MS = 220L
        const val AUTO_MIN_PREFIX = 2
        val IDENTIFIER_TAIL = Regex("[A-Za-z_][A-Za-z0-9_]*$")
    }
}
