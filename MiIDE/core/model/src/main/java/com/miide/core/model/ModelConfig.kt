package com.miide.core.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * 参数化模型配置 —— 核心设计：
 * 每个模型都是独立配置项，可逐模型调整思考强度、上下文窗口、max_tokens 等。
 * 适用于任意 OpenAI 兼容三方（如一家聚合商同时提供 DeepSeek + GLM-5.2）。
 */
@Serializable
data class ModelConfig(
    val id: String = "",
    val displayName: String = id,
    val contextWindow: Long? = null,
    val maxTokens: Long? = null,
    val reasoningEffort: String? = null,
    val temperature: Double? = null,
    val topP: Double? = null,
    val toolsEnabled: Boolean = true,
    val multimodal: Boolean = false,
    val cacheEnabled: Boolean = false,
    val enabled: Boolean = true,
    val extraParams: Map<String, String> = emptyMap(),
    val inputPricePerM: Double = 0.0,
    val outputPricePerM: Double = 0.0
)
