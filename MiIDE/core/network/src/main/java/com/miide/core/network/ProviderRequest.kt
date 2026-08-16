package com.miide.core.network

import com.miide.core.model.ChatMessage

/**
 * 工具（function calling）定义。
 */
data class ToolSpec(
    val name: String,
    val description: String = "",
    val parametersJson: String? = null
)

/**
 * 供应商请求。
 */
data class ProviderRequest(
    val modelId: String,
    val messages: List<ChatMessage>,
    val systemPrompt: String? = null,
    val temperature: Double? = null,
    val maxTokens: Int? = null,
    val reasoningEffort: String? = null,
    val tools: List<ToolSpec>? = null,
    val stream: Boolean = true
)
