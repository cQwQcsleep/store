package com.miide.core.network

import kotlin.math.pow

/**
 * 慢响应重试策略：指数退避 + 上限。
 * 部分供应商响应时间长或偶发限流，需要自动重试（核心卖点之一）。
 */
class RetryPolicy(
    val maxAttempts: Int = 3,
    val baseDelayMs: Long = 500,
    val maxDelayMs: Long = 8_000
) {
    fun delayFor(attempt: Int): Long {
        val exp = (attempt - 1).coerceIn(0, 10)
        val d = baseDelayMs * 2.0.pow(exp)
        return d.toLong().coerceAtMost(maxDelayMs)
    }
}
