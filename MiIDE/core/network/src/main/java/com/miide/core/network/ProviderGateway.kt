package com.miide.core.network

import com.miide.core.model.ProviderConfig
import com.miide.core.model.UsageRecord
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay

/**
 * 供应商网关：
 * - 慢响应自动重试（指数退避）；
 * - 多 Key 轮询 / 失败切换（聚合网关基础，M3 完善路由与限额）；
 * - 用量轨迹落库。
 */
class ProviderGateway(
    private val factory: ProviderFactory,
    private val retryPolicy: RetryPolicy,
    private val usageSink: UsageSink
) {
    suspend fun chat(
        provider: ProviderConfig,
        request: ProviderRequest,
        onEvent: suspend (ProviderEvent) -> Unit
    ): ProviderResult {
        var last: ProviderResult? = null
        val keys = provider.allActiveKeys.ifEmpty { listOf("") }

        for (attempt in 1..retryPolicy.maxAttempts) {
            val key = keys[(attempt - 1) % keys.size]
            val attemptConfig = provider.copy(apiKey = key, apiKeys = emptyList())
            val p = factory.create(attemptConfig)

            val result = try {
                p.chat(request, onEvent)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                ProviderResult(false, e.message ?: "未知错误", retryable = true)
            }

            if (result.success) {
                recordUsage(attemptConfig, request, result)
                return result
            }
            last = result
            if (!result.retryable) break
            if (attempt < retryPolicy.maxAttempts) delay(retryPolicy.delayFor(attempt))
        }
        return last ?: ProviderResult(false, "请求失败")
    }

    private suspend fun recordUsage(
        config: ProviderConfig,
        request: ProviderRequest,
        result: ProviderResult
    ) {
        val model = config.model(request.modelId)
        val inputCost = result.promptTokens / 1_000_000.0 * (model?.inputPricePerM ?: 0.0)
        val outputCost = result.completionTokens / 1_000_000.0 * (model?.outputPricePerM ?: 0.0)
        usageSink.record(
            UsageRecord(
                providerId = config.id,
                modelId = request.modelId,
                promptTokens = result.promptTokens,
                completionTokens = result.completionTokens,
                cachedTokens = result.cachedTokens,
                totalTokens = result.promptTokens + result.completionTokens,
                latencyMs = result.latencyMs,
                costUsd = inputCost + outputCost,
                success = result.success
            )
        )
    }
}
