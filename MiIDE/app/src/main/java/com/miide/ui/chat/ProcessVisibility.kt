package com.miide.ui.chat

import com.miide.ui.editor.EditorBridge
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/** AI 动作类型（实时动作流，现场直播）。 */
enum class ActivityKind {
    THINK,      // 思考
    TOOL,       // 工具调用（通用 / MCP）
    READ,       // 读取文件 / 搜索
    WRITE,      // 写入 / 编辑文件
    RUN,        // 运行代码 / 命令
    FETCH,      // 抓取网页
    DONE,       // 本轮完成
    STOPPED,    // 已停止
    QUEUE       // 意见入队（建议队列）
}

/** 一条 AI 动作记录。 */
data class ActivityEntry(
    val kind: ActivityKind,
    val text: String,
    val ts: Long = System.currentTimeMillis()
)

/** 待确认的 diff 预览（AI 建议的编辑）。 */
data class PendingDiff(
    val summary: String,
    val before: String,
    val after: String
)

/** diff 行类型。 */
enum class DiffLineKind { CONTEXT, ADD, REMOVE }

/** 行级 diff 结果。 */
data class DiffLine(val kind: DiffLineKind, val text: String)

/**
 * 工具名 → 可读动作描述。
 * 把内部工具名（如 filesystem.read_file）翻译成用户看得懂的动作文案。
 */
fun describeTool(name: String): Pair<ActivityKind, String> = when {
    name == "filesystem.read_file" -> ActivityKind.READ to "读取文件"
    name == "filesystem.write_file" -> ActivityKind.WRITE to "写入文件"
    name == "filesystem.list_dir" -> ActivityKind.READ to "列出目录"
    name == "code.run" -> ActivityKind.RUN to "运行代码"
    name == "browser.fetch_url" -> ActivityKind.FETCH to "抓取网页"
    name.startsWith("mcp") || name.contains("__") -> ActivityKind.TOOL to "调用 MCP 工具 $name"
    else -> ActivityKind.TOOL to "调用工具 $name"
}

/**
 * 行级 diff 计算（LCS）。
 *
 * 用于「改动 diff 预览」：展示 AI 建议的编辑相对当前文档的新增 / 删除 / 上下文行。
 */
object DiffUtils {

    /** 计算行级 diff；行数过大时截取前后各 [LIMIT] 行，避免 LCS 过慢。 */
    fun compute(before: String, after: String): List<DiffLine> {
        val a = clip(before).lines()
        val b = clip(after).lines()
        val n = a.size
        val m = b.size

        val dp = Array(n + 1) { IntArray(m + 1) }
        for (i in n - 1 downTo 0) {
            for (j in m - 1 downTo 0) {
                dp[i][j] = if (a[i] == b[j]) dp[i + 1][j + 1] + 1 else maxOf(dp[i + 1][j], dp[i][j + 1])
            }
        }

        val out = mutableListOf<DiffLine>()
        var i = 0
        var j = 0
        while (i < n && j < m) {
            when {
                a[i] == b[j] -> {
                    out += DiffLine(DiffLineKind.CONTEXT, a[i]); i++; j++
                }
                dp[i + 1][j] >= dp[i][j + 1] -> {
                    out += DiffLine(DiffLineKind.REMOVE, a[i]); i++
                }
                else -> {
                    out += DiffLine(DiffLineKind.ADD, b[j]); j++
                }
            }
        }
        while (i < n) { out += DiffLine(DiffLineKind.REMOVE, a[i]); i++ }
        while (j < m) { out += DiffLine(DiffLineKind.ADD, b[j]); j++ }
        return out
    }

    private fun clip(text: String): String {
        val lines = text.lines()
        if (lines.size <= LIMIT * 2) return text
        val head = lines.take(LIMIT)
        val tail = lines.takeLast(LIMIT)
        return (head + listOf("…（中间省略 ${lines.size - LIMIT * 2} 行）…") + tail).joinToString("\n")
    }

    private const val LIMIT = 300
}

/**
 * 读取编辑器当前文本（主线程安全，供 diff 预览用）。
 */
suspend fun editorCurrentText(): String? = suspendCancellableCoroutine { cont ->
    EditorBridge.readCurrentText { cont.resume(it) }
}

/**
 * 读取编辑器当前光标偏移（字符）。
 */
suspend fun editorCursorOffset(): Int? = suspendCancellableCoroutine { cont ->
    EditorBridge.readCursorOffset { cont.resume(it) }
}
