package com.miide.core.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "usage_records",
    indices = [Index(value = ["createdAt"]), Index(value = ["providerId"])]
)
data class UsageRecordEntity(
    @PrimaryKey val id: String,
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
