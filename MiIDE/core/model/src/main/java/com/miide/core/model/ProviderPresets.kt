package com.miide.core.model

/**
 * 内置供应商预设 —— M3「国内主流供应商深度适配」。
 *
 * 选中预设即可一键填充正确的 Base URL、官方模型、上下文窗口、
 * 思考强度与特殊请求头（如通义千问的 SSE 头），降低配置门槛。
 *
 * 数据来自各供应商公开文档，价格为单位大致估算（用于用量统计），
 * 实际以官方计费为准。
 */
object ProviderPresets {

    /** 单个模型预设。 */
    data class ModelPreset(
        val id: String,
        val displayName: String? = null,
        val contextWindow: Long? = null,
        val maxTokens: Long? = null,
        val reasoningEffort: String? = null,
        val cacheEnabled: Boolean = false,
        val inputPricePerM: Double = 0.0,
        val outputPricePerM: Double = 0.0
    ) {
        fun toModelConfig(): ModelConfig = ModelConfig(
            id = id,
            displayName = displayName ?: id,
            contextWindow = contextWindow,
            maxTokens = maxTokens,
            reasoningEffort = reasoningEffort,
            cacheEnabled = cacheEnabled,
            inputPricePerM = inputPricePerM,
            outputPricePerM = outputPricePerM
        )
    }

    /** 供应商预设。 */
    data class Preset(
        val type: ProviderType,
        val name: String,
        val protocol: ProviderProtocol = ProviderProtocol.OPENAI_COMPAT,
        val baseUrl: String,
        val extraHeaders: Map<String, String> = emptyMap(),
        val models: List<ModelPreset>
    ) {
        /** 生成可直接入库的配置（不含 Key）。 */
        fun toProviderConfig(): ProviderConfig {
            val configs = models.map { it.toModelConfig() }
            return ProviderConfig(
                name = name,
                type = type,
                protocol = protocol,
                baseUrl = baseUrl,
                extraHeaders = extraHeaders,
                models = configs,
                defaultModelId = configs.firstOrNull()?.id
            )
        }
    }

    /** 国内主流供应商 + OpenAI（自研适配起点），按常用度排序。 */
    val all: List<Preset> = listOf(
        Preset(
            type = ProviderType.DEEPSEEK,
            name = "DeepSeek",
            baseUrl = "https://api.deepseek.com/v1",
            models = listOf(
                ModelPreset(
                    id = "deepseek-chat",
                    displayName = "DeepSeek-V3",
                    contextWindow = 64000,
                    maxTokens = 8192,
                    cacheEnabled = true,
                    inputPricePerM = 0.27,
                    outputPricePerM = 1.10
                ),
                ModelPreset(
                    id = "deepseek-reasoner",
                    displayName = "DeepSeek-R1",
                    contextWindow = 64000,
                    maxTokens = 8192,
                    cacheEnabled = true,
                    inputPricePerM = 0.55,
                    outputPricePerM = 2.19
                )
            )
        ),
        Preset(
            type = ProviderType.ZHIPU_GLM,
            name = "智谱 GLM",
            baseUrl = "https://open.bigmodel.cn/api/paas/v4",
            models = listOf(
                ModelPreset(
                    id = "glm-4.6",
                    displayName = "GLM-4.6（思考）",
                    contextWindow = 200000,
                    maxTokens = 8192,
                    reasoningEffort = "high",
                    inputPricePerM = 2.0,
                    outputPricePerM = 6.0
                ),
                ModelPreset(
                    id = "glm-4.5",
                    displayName = "GLM-4.5",
                    contextWindow = 200000,
                    maxTokens = 8192,
                    inputPricePerM = 2.0,
                    outputPricePerM = 6.0
                ),
                ModelPreset(
                    id = "glm-4.5-air",
                    displayName = "GLM-4.5-Air",
                    contextWindow = 200000,
                    maxTokens = 8192,
                    cacheEnabled = true,
                    inputPricePerM = 0.6,
                    outputPricePerM = 2.0
                ),
                ModelPreset(
                    id = "glm-4-flash",
                    displayName = "GLM-4-Flash（免费）",
                    contextWindow = 128000,
                    maxTokens = 4096
                )
            )
        ),
        Preset(
            type = ProviderType.KIMI,
            name = "Kimi",
            baseUrl = "https://api.moonshot.cn/v1",
            models = listOf(
                ModelPreset(
                    id = "kimi-k2",
                    displayName = "Kimi K2",
                    contextWindow = 128000,
                    maxTokens = 8192,
                    inputPricePerM = 4.0,
                    outputPricePerM = 16.0
                ),
                ModelPreset(
                    id = "moonshot-v1-128k",
                    displayName = "Moonshot v1 128K",
                    contextWindow = 128000,
                    maxTokens = 4096,
                    inputPricePerM = 12.0,
                    outputPricePerM = 12.0
                ),
                ModelPreset(
                    id = "moonshot-v1-32k",
                    displayName = "Moonshot v1 32K",
                    contextWindow = 32000,
                    maxTokens = 4096,
                    inputPricePerM = 4.0,
                    outputPricePerM = 4.0
                ),
                ModelPreset(
                    id = "moonshot-v1-8k",
                    displayName = "Moonshot v1 8K",
                    contextWindow = 8000,
                    maxTokens = 4096,
                    inputPricePerM = 2.0,
                    outputPricePerM = 2.0
                )
            )
        ),
        Preset(
            type = ProviderType.QWEN,
            name = "通义千问",
            baseUrl = "https://dashscope.aliyuncs.com/compatible-mode/v1",
            // DashScope OpenAI 兼容模式需要该头才会以 SSE 流式返回
            extraHeaders = mapOf("X-DashScope-SSE" to "enable"),
            models = listOf(
                ModelPreset(
                    id = "qwen3-max",
                    displayName = "Qwen3 Max（思考）",
                    contextWindow = 32768,
                    maxTokens = 8192,
                    reasoningEffort = "high",
                    inputPricePerM = 4.5,
                    outputPricePerM = 18.0
                ),
                ModelPreset(
                    id = "qwen3-plus",
                    displayName = "Qwen3 Plus",
                    contextWindow = 131072,
                    maxTokens = 8192,
                    reasoningEffort = "medium",
                    inputPricePerM = 1.2,
                    outputPricePerM = 6.0
                ),
                ModelPreset(
                    id = "qwen3-turbo",
                    displayName = "Qwen3 Turbo",
                    contextWindow = 131072,
                    maxTokens = 8192,
                    reasoningEffort = "medium",
                    inputPricePerM = 0.8,
                    outputPricePerM = 4.0
                ),
                ModelPreset(
                    id = "qwen-max",
                    displayName = "Qwen Max",
                    contextWindow = 32000,
                    maxTokens = 8192,
                    inputPricePerM = 2.4,
                    outputPricePerM = 9.6
                ),
                ModelPreset(
                    id = "qwen-plus",
                    displayName = "Qwen Plus",
                    contextWindow = 131072,
                    maxTokens = 8192,
                    inputPricePerM = 0.8,
                    outputPricePerM = 2.0
                ),
                ModelPreset(
                    id = "qwen-turbo",
                    displayName = "Qwen Turbo",
                    contextWindow = 131072,
                    maxTokens = 8192,
                    inputPricePerM = 0.3,
                    outputPricePerM = 0.6
                )
            )
        ),
        Preset(
            type = ProviderType.OPENAI,
            name = "OpenAI",
            baseUrl = "https://api.openai.com/v1",
            models = listOf(
                ModelPreset(
                    id = "gpt-4o",
                    displayName = "GPT-4o",
                    contextWindow = 128000,
                    maxTokens = 4096,
                    inputPricePerM = 2.5,
                    outputPricePerM = 10.0
                ),
                ModelPreset(
                    id = "gpt-4o-mini",
                    displayName = "GPT-4o mini",
                    contextWindow = 128000,
                    maxTokens = 4096,
                    inputPricePerM = 0.15,
                    outputPricePerM = 0.6
                )
            )
        )
    )

    /** 按类型查预设；自定义类型返回 null。 */
    fun forType(type: ProviderType): Preset? = all.firstOrNull { it.type == type }
}
