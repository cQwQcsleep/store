package com.miide.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/**
 * OpenAI Chat Completions 兼容协议 DTO。
 * 覆盖绝大多数三方聚合商（DeepSeek / 智谱 GLM / Kimi / 通义 均兼容）。
 */

@Serializable
data class OpenAiChatRequest(
    val model: String,
    val messages: List<OpenAiMessage>,
    val temperature: Double? = null,
    @SerialName("max_tokens") val maxTokens: Int? = null,
    @SerialName("max_completion_tokens") val maxCompletionTokens: Int? = null,
    val stream: Boolean = true,
    @SerialName("reasoning_effort") val reasoningEffort: String? = null,
    // 通义千问 Qwen3：以 enable_thinking 开关思考模式（而非 reasoning_effort）
    @SerialName("enable_thinking") val enableThinking: Boolean? = null,
    val tools: List<OpenAiTool>? = null
)

@Serializable
data class OpenAiMessage(
    val role: String,
    val content: String? = null,
    @SerialName("tool_call_id") val toolCallId: String? = null,
    @SerialName("tool_calls") val toolCalls: List<OpenAiToolCall>? = null
)

@Serializable
data class OpenAiTool(
    val type: String = "function",
    val function: OpenAiFunction
)

@Serializable
data class OpenAiFunction(
    val name: String,
    val description: String = "",
    val parameters: JsonObject? = null
)

@Serializable
data class OpenAiChatChunk(
    val id: String? = null,
    val choices: List<OpenAiChunkChoice> = emptyList(),
    val usage: OpenAiUsage? = null
)

@Serializable
data class OpenAiChunkChoice(
    val delta: OpenAiDelta = OpenAiDelta(),
    @SerialName("finish_reason") val finishReason: String? = null
)

@Serializable
data class OpenAiDelta(
    val role: String? = null,
    val content: String? = null,
    @SerialName("reasoning_content") val reasoningContent: String? = null,
    val reasoning: String? = null,
    @SerialName("tool_calls") val toolCalls: List<OpenAiToolCallDelta>? = null
)

@Serializable
data class OpenAiToolCallDelta(
    val id: String? = null,
    val function: OpenAiFunctionDelta? = null
)

@Serializable
data class OpenAiFunctionDelta(
    val name: String? = null,
    val arguments: String? = null
)

@Serializable
data class OpenAiChatResponse(
    val id: String? = null,
    val choices: List<OpenAiResponseChoice> = emptyList(),
    val usage: OpenAiUsage? = null
)

@Serializable
data class OpenAiResponseChoice(
    val message: OpenAiResponseMessage = OpenAiResponseMessage(),
    @SerialName("finish_reason") val finishReason: String? = null
)

@Serializable
data class OpenAiResponseMessage(
    val role: String? = null,
    val content: String? = null,
    @SerialName("reasoning_content") val reasoningContent: String? = null,
    val reasoning: String? = null,
    @SerialName("tool_calls") val toolCalls: List<OpenAiToolCall>? = null
)

@Serializable
data class OpenAiToolCall(
    val id: String? = null,
    val function: OpenAiFunctionDelta? = null
)

@Serializable
data class OpenAiUsage(
    @SerialName("prompt_tokens") val promptTokens: Long = 0,
    @SerialName("completion_tokens") val completionTokens: Long = 0,
    @SerialName("total_tokens") val totalTokens: Long = 0,
    @SerialName("prompt_cache_hit_tokens") val promptCacheHitTokens: Long? = null,
    @SerialName("cached_tokens") val cachedTokens: Long? = null
)

@Serializable
data class OpenAiModelsResponse(val data: List<OpenAiModelItem> = emptyList())

@Serializable
data class OpenAiModelItem(
    val id: String,
    @SerialName("owned_by") val ownedBy: String? = null,
    @SerialName("context_window") val contextWindow: Long? = null,
    @SerialName("max_tokens") val maxTokens: Long? = null
)

@Serializable
data class OpenAiErrorResponse(val error: OpenAiErrorBody? = null)

@Serializable
data class OpenAiErrorBody(val message: String? = null, val type: String? = null)
