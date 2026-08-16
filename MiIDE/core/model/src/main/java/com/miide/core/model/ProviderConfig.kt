package com.miide.core.model

import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * 供应商配置。apiKey 在存储层加密保存（Keystore），
 * 本对象运行期使用已解密的密钥。
 */
@Serializable
data class ProviderConfig(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val type: ProviderType = ProviderType.CUSTOM,
    val protocol: ProviderProtocol = ProviderProtocol.OPENAI_COMPAT,
    val baseUrl: String = "",
    val apiKey: String = "",
    val apiKeys: List<String> = emptyList(),
    val extraHeaders: Map<String, String> = emptyMap(),
    val models: List<ModelConfig> = emptyList(),
    val templateJson: String? = null,
    val defaultModelId: String? = null,
    val enabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
) {
    val defaultModel: ModelConfig?
        get() = models.firstOrNull { it.id == defaultModelId && it.enabled }
            ?: models.firstOrNull { it.enabled }

    fun model(id: String?): ModelConfig? =
        models.firstOrNull { it.id == id && it.enabled } ?: defaultModel

    val allActiveKeys: List<String>
        get() = buildList {
            if (apiKey.isNotBlank()) add(apiKey)
            addAll(apiKeys.filter { it.isNotBlank() })
        }
}
