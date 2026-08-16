package com.miide.core.model

import kotlinx.serialization.Serializable

/**
 * 供应商品牌类型（用于「深度适配」标记，如国内厂商的特殊行为）。
 */
@Serializable
enum class ProviderType {
    OPENAI,
    DEEPSEEK,
    ZHIPU_GLM,
    KIMI,
    QWEN,
    ANTHROPIC,
    GEMINI,
    CUSTOM;

    val displayName: String
        get() = when (this) {
            OPENAI -> "OpenAI"
            DEEPSEEK -> "DeepSeek"
            ZHIPU_GLM -> "智谱 GLM"
            KIMI -> "Kimi"
            QWEN -> "通义千问"
            ANTHROPIC -> "Anthropic"
            GEMINI -> "Gemini"
            CUSTOM -> "自定义"
        }
}

/**
 * 协议类型：决定请求/响应的编解码方式。
 */
@Serializable
enum class ProviderProtocol {
    /** OpenAI Chat Completions 兼容协议（覆盖绝大多数三方聚合商） */
    OPENAI_COMPAT,

    /** Anthropic Messages API */
    ANTHROPIC,

    /** Google Gemini generateContent API */
    GEMINI,

    /** 用户自定义 JSON 模板 */
    CUSTOM_TEMPLATE,

    /** 聚合网关（多 Key / 多供应商转发） */
    AGGREGATE;

    val displayName: String
        get() = when (this) {
            OPENAI_COMPAT -> "OpenAI 兼容"
            ANTHROPIC -> "Anthropic"
            GEMINI -> "Gemini"
            CUSTOM_TEMPLATE -> "自定义模板"
            AGGREGATE -> "聚合网关"
        }
}
