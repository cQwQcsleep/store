package com.miide.core.network

import com.miide.core.model.BudgetPolicy
import com.miide.core.model.ProviderConfig
import kotlinx.coroutines.CancellationException

/**
 * 聚合网关：
 * - **路由**：按候选供应商顺序（调用方按优先级排好）选择可用供应商；
 * - **失败切换**：单个供应商多 Key 重试仍失败后，自动切换到下一个候选；
 * - **限额**：日 token / 月预算超限直接拒绝，防止跑飞。
 *
 * 复用 [ProviderGateway] 的慢响应重试、多 Key 轮询与上下文缓存。
 */
class AggregateGateway(
    private val gateway: ProviderGateway,
    private val stats: BudgetStats
) {

    suspend fun chat(
        candidates: List<ProviderConfig>,
        request: ProviderRequest,
        policy: BudgetPolicy,
        onEvent: suspend (ProviderEvent) -> Unit
    ): ProviderResult {
        // 1) 限额检查：超限直接拒绝，不再发起网络请求
        checkBudget(policy)?.let { return ProviderResult(false, it, retryable = false) }

        // 2) 路由：活动供应商优先，其余按启用状态顺序作为后备
        val usable = candidates.filter { it.enabled }
        if (usable.isEmpty()) {
            return ProviderResult(
                false,
                "没有可用的 AI 供应商，请先在「AI 设置」中添加",
                retryable = false
            )
        }

        // 3) 失败切换：逐个候选尝试，全部失败才报错
        var last: ProviderResult? = null
        for (provider in usable) {
            val r = try {
                gateway.chat(provider, request, onEvent)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                ProviderResult(false, e.message ?: "未知错误", retryable = true)
            }
            if (r.success) return r
            last = r
            // 鉴权/参数类错误换下一个供应商也可能有意义，继续尝试后续候选
        }
        return last ?: ProviderResult(false, "所有供应商均不可用", retryable = false)
    }

    /** 返回超限原因；未超限返回 null。 */
    private suspend fun checkBudget(policy: BudgetPolicy): String? {
        if (!policy.enabled) return null
        policy.dailyTokenLimit?.let { limit ->
            val today = stats.todayTokens()
            if (today >= limit) {
                return "已达今日 token 限额（$today / $limit），请明日再试或调整限额"
            }
        }
        policy.monthlyCostLimitUsd?.let { limit ->
            val month = stats.monthCostUsd()
            if (month >= limit) {
                return "已达本月预算上限（$${trimDollar(month)} / $${trimDollar(limit)}）"
            }
        }
        return null
    }

    private fun trimDollar(v: Double): String =
        String.format("%.2f", v)
}
