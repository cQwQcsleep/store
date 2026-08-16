package com.miide.core.network.provider

import com.miide.core.model.ProviderConfig
import io.ktor.client.HttpClient
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * 模板配置（用户自定义供应商模板 JSON）：
 * ```json
 * {
 *   "chatPath": "/v1/chat/completions",
 *   "modelsPath": "/v1/models",
 *   "apiKeyHeader": "Authorization",
 *   "authScheme": "Bearer"
 * }
 * ```
 */
@Serializable
private data class TemplateConfig(
    val chatPath: String? = null,
    val modelsPath: String? = null,
    val apiKeyHeader: String? = null,
    val authScheme: String? = null
)

/**
 * 自定义供应商模板适配器：基于 OpenAI 兼容协议，
 * 允许用户覆盖端点路径与鉴权方式，适配任意三方聚合商。
 */
class CustomTemplateProvider(
    config: ProviderConfig,
    private val client: HttpClient,
    private val json: Json
) : OpenAiCompatProvider(config, client, json) {

    override val protocolName: String = "自定义模板"

    private val template: TemplateConfig? = config.templateJson?.let { raw ->
        runCatching { json.decodeFromString<TemplateConfig>(raw) }.getOrNull()
    }

    override fun chatUrl(): String {
        val path = template?.chatPath?.takeIf { it.isNotBlank() } ?: "/chat/completions"
        return config.baseUrl.trimEnd('/') + "/" + path.trimStart('/')
    }

    override fun modelsUrl(): String {
        val path = template?.modelsPath?.takeIf { it.isNotBlank() } ?: "/models"
        return config.baseUrl.trimEnd('/') + "/" + path.trimStart('/')
    }

    override fun authHeaders(key: String): Map<String, String> {
        if (key.isBlank()) return emptyMap()
        val headerName = template?.apiKeyHeader?.takeIf { it.isNotBlank() } ?: "Authorization"
        val scheme = template?.authScheme?.takeIf { it.isNotBlank() } ?: "Bearer"
        val value = if (headerName.equals("Authorization", ignoreCase = true)) "$scheme $key" else key
        return mapOf(headerName to value)
    }
}
