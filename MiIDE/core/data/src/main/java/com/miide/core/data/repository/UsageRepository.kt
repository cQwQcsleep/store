package com.miide.core.data.repository

import com.miide.core.data.local.dao.UsageDao
import com.miide.core.data.local.dao.UsageTotals
import com.miide.core.data.local.entity.UsageRecordEntity
import com.miide.core.model.UsageRecord
import com.miide.core.network.UsageSink
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 用量轨迹仓库。实现 [UsageSink]，由网络层在每次请求结束后回调落库。
 */
class UsageRepository(
    private val dao: UsageDao
) : UsageSink {

    fun observeRecent(limit: Int = 100): Flow<List<UsageRecord>> =
        dao.observeRecent(limit).map { list -> list.map { it.toModel() } }

    fun observeRecentByProvider(providerId: String, limit: Int = 50): Flow<List<UsageRecord>> =
        dao.observeRecentByProvider(providerId, limit).map { list -> list.map { it.toModel() } }

    fun observeTotals(): Flow<UsageTotals> = dao.observeTotals()

    override suspend fun record(record: UsageRecord) {
        dao.insert(
            UsageRecordEntity(
                id = record.id,
                providerId = record.providerId,
                modelId = record.modelId,
                requestId = record.requestId,
                promptTokens = record.promptTokens,
                completionTokens = record.completionTokens,
                cachedTokens = record.cachedTokens,
                totalTokens = record.totalTokens,
                latencyMs = record.latencyMs,
                costUsd = record.costUsd,
                success = record.success,
                createdAt = record.createdAt
            )
        )
    }

    suspend fun deleteBefore(before: Long) = dao.deleteBefore(before)

    suspend fun clear() = dao.clear()

    private fun UsageRecordEntity.toModel() = UsageRecord(
        id = id,
        providerId = providerId,
        modelId = modelId,
        requestId = requestId,
        promptTokens = promptTokens,
        completionTokens = completionTokens,
        cachedTokens = cachedTokens,
        totalTokens = totalTokens,
        latencyMs = latencyMs,
        costUsd = costUsd,
        success = success,
        createdAt = createdAt
    )
}
