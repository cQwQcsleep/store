package com.miide.core.network

import com.miide.core.model.ProviderProtocol
import com.miide.core.model.ProviderConfig
import com.miide.core.network.provider.CustomTemplateProvider
import com.miide.core.network.provider.OpenAiCompatProvider
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

/**
 * 供应商工厂：按协议创建对应适配器。
 * Anthropic / Gemini 原生协议在后续里程碑实现（M1 先用 OpenAI 兼容兜底）。
 */
class ProviderFactory(
    private val client: HttpClient,
    private val json: Json
) {
    fun create(config: ProviderConfig): AiProvider = when (config.protocol) {
        ProviderProtocol.CUSTOM_TEMPLATE -> CustomTemplateProvider(config, client, json)
        else -> OpenAiCompatProvider(config, client, json)
    }
}
