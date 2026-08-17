package com.miide.core.runtime

/** 一次代码运行的结果。 */
data class RunResult(
    val exitCode: Int,
    val stdout: String,
    val stderr: String,
    val durationMs: Long,
    val timedOut: Boolean = false
) {
    val success: Boolean get() = exitCode == 0 && !timedOut
}

/** 运行选项。 */
data class RunOptions(
    /** 超时毫秒，默认 5 秒。 */
    val timeoutMs: Long = 5_000
)

/**
 * 代码运行时抽象：每种语言一个实现。
 *
 * 供「一键运行」、AI 工具（code.run）等统一调用，屏蔽底层引擎差异。
 */
interface CodeRuntime {
    /** 运行时标识，如 js / lua / python。 */
    val id: String

    /** 显示名称，如 JavaScript。 */
    val displayName: String

    /** 关联的文件扩展名（含点），用于按文件类型自动选择运行时。 */
    val fileExtensions: List<String>

    /** 是否可用（原生库未加载、依赖缺失等场景下为 false）。 */
    val available: Boolean

    /** 执行一段代码，返回结果。 */
    suspend fun execute(code: String, options: RunOptions = RunOptions()): RunResult
}
