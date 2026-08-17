package com.miide.core.network

/**
 * 限额统计来源：聚合网关据此做日 token / 月预算检查。
 * 由数据层（用量轨迹）实现，避免网络层依赖数据层。
 */
interface BudgetStats {
    /** 今日（本地时区 0 点起）已消耗 token 总数。 */
    suspend fun todayTokens(): Long

    /** 本月（本地时区 1 日起）已消耗成本（USD）。 */
    suspend fun monthCostUsd(): Double
}
