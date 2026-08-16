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
    val error: String? = null
)
