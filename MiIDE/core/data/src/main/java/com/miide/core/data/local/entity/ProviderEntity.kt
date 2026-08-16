package com.miide.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 供应商配置实体。apiKey / apiKeys 为 Keystore 加密后的 Base64 密文。
 */
@Entity(tableName = "providers")
data class ProviderEntity(
    @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val protocol: String,
    val baseUrl: String,
    val apiKeyEnc: String? = null,
    val apiKeysEnc: String? = null,
    val extraHeadersJson: String = "{}",
    val modelsJson: String = "[]",
    val templateJson: String? = null,
    val defaultModelId: String? = null,
    val enabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
