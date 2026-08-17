package com.miide.core.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
enum class ChatRole { SYSTEM, USER, ASSISTANT, TOOL }

@Serializable
enum class MessageStatus { PENDING, STREAMING, COMPLETED, ERROR, INTERRUPTED }

/**
 * 单次工具调用（assistant 消息中可包含多个工具调用）。
 */
@Serializable
data class ToolCall(
    val id: String? = null,
    val name: String? = null,
    val arguments: String = ""
)

/**
 * 对话消息。reasoning 用于存放思考过程（思考强度可见）。
 */
@Serializable
data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val role: ChatRole = ChatRole.USER,
    val content: String = "",
    val reasoning: String? = null,
    val modelId: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val status: MessageStatus = MessageStatus.COMPLETED,
    val error: String? = null,
    /** 工具调用（assistant 发起工具调用时非空）。 */
    val toolCalls: List<ToolCall>? = null,
    /** 工具回复消息：对应助理端工具调用 id。 */
    val toolCallId: String? = null
) {
    val isUser: Boolean get() = role == ChatRole.USER
}
