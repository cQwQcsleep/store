package com.miide.core.model

import kotlinx.serialization.Serializable

/**
 * 聚合网关限额策略（防跑飞）。
 * - [dailyTokenLimit]：每日 token 上限，超出后拒绝请求；
 * - [monthlyCostLimitUsd]：每月预算上限（USD），超出后拒绝请求。
 */
@Serializable
data class BudgetPolicy(
    val dailyTokenLimit: Long? = null,
    val monthlyCostLimitUsd: Double? = null
) {
    val enabled: Boolean
        get() = dailyTokenLimit != null || monthlyCostLimitUsd != null

    companion object {
        val Disabled = BudgetPolicy()
    }
}
