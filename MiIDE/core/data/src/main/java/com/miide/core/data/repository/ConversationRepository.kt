package com.miide.core.data.repository

import com.miide.core.data.local.dao.ConversationDao
import com.miide.core.data.local.dao.MessageDao
import com.miide.core.data.local.entity.ConversationEntity
import com.miide.core.data.local.entity.MessageEntity
import com.miide.core.model.ChatConversation
import com.miide.core.model.ChatMessage
import com.miide.core.model.ChatRole
import com.miide.core.model.MessageStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 对话与消息仓库。
 *
 * 消息实体归属某个会话，因此写入接口显式传入 conversationId；
 * 读取返回的 [ChatMessage] 不携带 conversationId（UI 层无需感知）。
 */
class ConversationRepository(
    private val conversationDao: ConversationDao,
    private val messageDao: MessageDao
) {

    // ---- 会话 ----

    fun observeConversations(): Flow<List<ChatConversation>> =
        conversationDao.observeAll().map { list -> list.map { it.toModel() } }

    fun observeConversation(id: String): Flow<ChatConversation?> =
        conversationDao.observeById(id).map { it?.toModel() }

    suspend fun getConversation(id: String): ChatConversation? =
        conversationDao.getById(id)?.toModel()

    suspend fun upsertConversation(conversation: ChatConversation) {
        conversationDao.upsert(conversation.toEntity())
    }

    suspend fun updateTitle(id: String, title: String) {
        conversationDao.updateTitle(id, title, System.currentTimeMillis())
    }

    suspend fun deleteConversation(id: String) {
        conversationDao.deleteById(id)
        messageDao.deleteByConversation(id)
    }

    // ---- 消息 ----

    fun observeMessages(conversationId: String): Flow<List<ChatMessage>> =
        messageDao.observeByConversation(conversationId).map { list -> list.map { it.toModel() } }

    suspend fun getMessages(conversationId: String): List<ChatMessage> =
        messageDao.getByConversation(conversationId).map { it.toModel() }

    suspend fun upsertMessage(conversationId: String, message: ChatMessage) {
        messageDao.upsert(message.toEntity(conversationId))
    }

    suspend fun upsertMessages(conversationId: String, messages: List<ChatMessage>) {
        messageDao.upsertAll(messages.map { it.toEntity(conversationId) })
    }

    suspend fun deleteMessage(id: String) {
        messageDao.deleteById(id)
    }

    private fun ChatConversation.toEntity() = ConversationEntity(
        id = id,
        title = title,
        providerConfigId = providerConfigId,
        modelId = modelId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    private fun ConversationEntity.toModel() = ChatConversation(
        id = id,
        title = title,
        providerConfigId = providerConfigId,
        modelId = modelId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )

    private fun ChatMessage.toEntity(conversationId: String) = MessageEntity(
        id = id,
        conversationId = conversationId,
        role = role.name,
        content = content,
        reasoning = reasoning,
        modelId = modelId,
        createdAt = createdAt,
        status = status.name,
        error = error
    )

    private fun MessageEntity.toModel() = ChatMessage(
        id = id,
        role = role.let { runCatching { ChatRole.valueOf(it) }.getOrDefault(ChatRole.USER) },
        content = content,
        reasoning = reasoning,
        modelId = modelId,
        createdAt = createdAt,
        status = status.let { runCatching { MessageStatus.valueOf(it) }.getOrDefault(MessageStatus.COMPLETED) },
        error = error
    )
}
