package com.miide.core.network

import com.miide.core.model.UsageRecord

/**
 * 用量落库接口，由数据层实现（避免网络层依赖数据层）。
 */
interface UsageSink {
    suspend fun record(usage: UsageRecord)
}
