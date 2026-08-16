package com.miide.core.model

import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * 用量轨迹记录（请求耗时 / token / 费用），用于用量统计与缓存命中率观测。
 */
@Serializable
data class UsageRecord(
    val id: String = UUID.randomUUID().toString(),
    val providerId: String = "",
    val modelId: String = "",
    val requestId: String? = null,
    val promptTokens: Long = 0,
    val completionTokens: Long = 0,
    val cachedTokens: Long = 0,
    val totalTokens: Long = 0,
    val latencyMs: Long = 0,
    val costUsd: Double = 0.0,
    val success: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)
