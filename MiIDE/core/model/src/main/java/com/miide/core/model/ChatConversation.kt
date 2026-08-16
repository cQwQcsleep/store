package com.miide.core.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ChatConversation(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "新对话",
    val providerConfigId: String = "",
    val modelId: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
