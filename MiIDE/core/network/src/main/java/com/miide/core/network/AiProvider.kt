package com.miide.core.network

import com.miide.core.model.ProviderConfig

/**
 * 模型信息（能力探测结果）。
 */
data class ModelInfo(
    val id: String,
    val displayName: String = id,
    val contextWindow: Long? = null
)

/**
 * 一次调用结果（含用量）。
 */
data class ProviderResult(
    val success: Boolean,
    val error: String? = null,
    val retryable: Boolean = false,
    val promptTokens: Long = 0,
    val completionTokens: Long = 0,
    val cachedTokens: Long = 0,
    val latencyMs: Long = 0
)

/**
 * 供应商抽象：所有协议适配器（OpenAI 兼容 / 自定义模板 / 原生 / 聚合）实现此接口。
 */
interface AiProvider {
    val config: ProviderConfig
    val protocolName: String

    /** 流式对话；onEvent 实时回调事件 */
    suspend fun chat(request: ProviderRequest, onEvent: suspend (ProviderEvent) -> Unit): ProviderResult

    /** 拉取模型列表；失败时回退到配置内模型 */
    suspend fun listModels(): List<ModelInfo>
}
