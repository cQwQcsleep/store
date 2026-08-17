package com.miide.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miide.core.data.local.entity.UsageRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: UsageRecordEntity)

    @Query("SELECT * FROM usage_records ORDER BY createdAt DESC LIMIT :limit")
    fun observeRecent(limit: Int): Flow<List<UsageRecordEntity>>

    @Query("SELECT * FROM usage_records WHERE providerId = :providerId ORDER BY createdAt DESC LIMIT :limit")
    fun observeRecentByProvider(providerId: String, limit: Int): Flow<List<UsageRecordEntity>>

    @Query(
        """
        SELECT COALESCE(SUM(promptTokens), 0) AS promptTokens,
               COALESCE(SUM(completionTokens), 0) AS completionTokens,
               COALESCE(SUM(cachedTokens), 0) AS cachedTokens,
               COALESCE(SUM(totalTokens), 0) AS totalTokens,
               COALESCE(SUM(costUsd), 0) AS costUsd,
               COUNT(*) AS requestCount,
               COALESCE(AVG(latencyMs), 0) AS avgLatencyMs
        FROM usage_records
        """
    )
    fun observeTotals(): Flow<UsageTotals>

    @Query("DELETE FROM usage_records WHERE createdAt < :before")
    suspend fun deleteBefore(before: Long)

    @Query("DELETE FROM usage_records")
    suspend fun clear()

    /** 自 [from] 时刻起的累计 token（聚合网关日限额用）。 */
    @Query("SELECT COALESCE(SUM(totalTokens), 0) FROM usage_records WHERE createdAt >= :from")
    suspend fun sumTokensSince(from: Long): Long

    /** 自 [from] 时刻起的累计成本 USD（聚合网关月预算用）。 */
    @Query("SELECT COALESCE(SUM(costUsd), 0) FROM usage_records WHERE createdAt >= :from")
    suspend fun sumCostSince(from: Long): Double
}

/**
 * 用量汇总（DAO 投影）。
 */
data class UsageTotals(
    val promptTokens: Long,
    val completionTokens: Long,
    val cachedTokens: Long,
    val totalTokens: Long,
    val costUsd: Double,
    val requestCount: Int,
    val avgLatencyMs: Double
) {
    /** 缓存命中率 = cached / (cached + prompt)。 */
    val cacheHitRate: Double
        get() {
            val denom = cachedTokens + promptTokens
            return if (denom > 0) cachedTokens.toDouble() / denom else 0.0
        }
}
