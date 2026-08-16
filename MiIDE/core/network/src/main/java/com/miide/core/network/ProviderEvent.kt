package com.miide.core.network

/**
 * 流式事件。调用方通过 onEvent 实时接收（AI 过程可见的基础）。
 */
sealed interface ProviderEvent {
    /** 正文增量 */
    data class Delta(val content: String) : ProviderEvent

    /** 思考/推理增量（思考强度可见） */
    data class ReasoningDelta(val content: String) : ProviderEvent

    /** 工具调用（完整参数） */
    data class ToolCall(val id: String?, val name: String?, val arguments: String) : ProviderEvent

    /** 用量统计 */
    data class Usage(
        val promptTokens: Long,
        val completionTokens: Long,
        val cachedTokens: Long,
        val totalTokens: Long
    ) : ProviderEvent

    /** 错误（retryable 是否可重试） */
    data class Error(val message: String, val retryable: Boolean = false) : ProviderEvent

    /** 正常结束 */
    data object Done : ProviderEvent
}
