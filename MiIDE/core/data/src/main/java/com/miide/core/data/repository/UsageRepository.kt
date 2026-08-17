package com.miide.core.data.repository

import com.miide.core.data.local.dao.UsageDao
import com.miide.core.data.local.dao.UsageTotals
import com.miide.core.data.local.entity.UsageRecordEntity
import com.miide.core.model.UsageRecord
import com.miide.core.network.BudgetStats
import com.miide.core.network.UsageSink
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar

/**
 * 用量轨迹仓库。实现 [UsageSink]（网络层回调落库）与 [BudgetStats]（聚合网关限额统计）。
 */
class UsageRepository(
    private val dao: UsageDao
) : UsageSink, BudgetStats {

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

    /** 今日（本地时区 0 点起）累计 token。 */
    override suspend fun todayTokens(): Long = dao.sumTokensSince(startOfDay())

    /** 本月（本地时区 1 日起）累计成本 USD。 */
    override suspend fun monthCostUsd(): Double = dao.sumCostSince(startOfMonth())

    suspend fun deleteBefore(before: Long) = dao.deleteBefore(before)

    suspend fun clear() = dao.clear()

    private fun startOfDay(): Long = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    private fun startOfMonth(): Long = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

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
