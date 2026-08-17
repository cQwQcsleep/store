package com.miide.core.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "messages",
    indices = [Index(value = ["conversationId"])]
)
data class MessageEntity(
    @PrimaryKey val id: String,
    val conversationId: String,
    val role: String,
    val content: String = "",
    val reasoning: String? = null,
    val modelId: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val status: String = "COMPLETED",
    val error: String? = null,
    /** 工具调用列表（JSON 序列化，assistant 消息）。 */
    val toolCallsJson: String? = null,
    /** 工具回复消息：对应 assistant 端工具调用 id。 */
    val toolCallId: String? = null
)
