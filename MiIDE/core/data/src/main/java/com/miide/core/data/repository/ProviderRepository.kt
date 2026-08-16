package com.miide.core.data.repository

import com.miide.core.data.local.dao.ProviderDao
import com.miide.core.data.local.entity.ProviderEntity
import com.miide.core.data.security.SecureKeyStore
import com.miide.core.model.ModelConfig
import com.miide.core.model.ProviderConfig
import com.miide.core.model.ProviderProtocol
import com.miide.core.model.ProviderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

/**
 * 供应商配置仓库。
 *
 * API Key 采用 Keystore 加密后落库；读取时自动解密。
 * 加解密失败（如密钥被清除）时返回空 Key，不阻塞读取。
 */
class ProviderRepository(
    private val dao: ProviderDao,
    private val secureKeyStore: SecureKeyStore,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {

    fun observeAll(): Flow<List<ProviderConfig>> =
        dao.observeAll().map { entities -> entities.map { it.toConfig() } }

    suspend fun getAll(): List<ProviderConfig> =
        dao.getAll().map { it.toConfig() }

    suspend fun getById(id: String): ProviderConfig? =
        dao.getById(id)?.toConfig()

    suspend fun getEnabled(): List<ProviderConfig> =
        dao.getEnabled().map { it.toConfig() }

    suspend fun upsert(config: ProviderConfig) {
        dao.upsert(config.toEntity())
    }

    suspend fun setEnabled(id: String, enabled: Boolean) {
        dao.setEnabled(id, enabled)
    }

    suspend fun deleteById(id: String) {
        dao.deleteById(id)
    }

    private fun ProviderConfig.toEntity(): ProviderEntity = ProviderEntity(
        id = id,
        name = name,
        type = type.name,
        protocol = protocol.name,
        baseUrl = baseUrl,
        apiKeyEnc = apiKey.takeIf { it.isNotBlank() }?.let { secureKeyStore.encrypt(it) },
        apiKeysEnc = apiKeys.takeIf { it.isNotEmpty() }?.let {
            json.encodeToString(ListSerializer(String.serializer()), it)
                .let { plain -> secureKeyStore.encrypt(plain) }
        },
        extraHeadersJson = json.encodeToString(
            MapSerializer(String.serializer(), String.serializer()),
            extraHeaders
        ),
        modelsJson = json.encodeToString(ListSerializer(ModelConfig.serializer()), models),
        templateJson = templateJson,
        defaultModelId = defaultModelId,
        enabled = enabled,
        createdAt = createdAt
    )

    private fun ProviderEntity.toConfig(): ProviderConfig = ProviderConfig(
        id = id,
        name = name,
        type = type.let { runCatching { ProviderType.valueOf(it) }.getOrDefault(ProviderType.CUSTOM) },
        protocol = protocol.let { runCatching { ProviderProtocol.valueOf(it) }.getOrDefault(ProviderProtocol.OPENAI_COMPAT) },
        baseUrl = baseUrl,
        apiKey = apiKeyEnc?.let { secureKeyStore.decrypt(it) } ?: "",
        apiKeys = apiKeysEnc?.let { enc ->
            val plain = secureKeyStore.decrypt(enc) ?: return@let emptyList<String>()
            runCatching { json.decodeFromString(ListSerializer(String.serializer()), plain) }
                .getOrDefault(emptyList())
        } ?: emptyList(),
        extraHeaders = runCatching { json.decodeFromString<Map<String, String>>(extraHeadersJson) }.getOrDefault(emptyMap()),
        models = runCatching { json.decodeFromString(ListSerializer(ModelConfig.serializer()), modelsJson) }
            .getOrDefault(emptyList()),
        templateJson = templateJson,
        defaultModelId = defaultModelId,
        enabled = enabled,
        createdAt = createdAt
    )
}
