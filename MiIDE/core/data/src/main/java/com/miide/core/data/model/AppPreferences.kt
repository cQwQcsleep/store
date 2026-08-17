package com.miide.core.data.model

import kotlinx.serialization.Serializable

/**
 * 应用偏好设置，通过 DataStore 持久化。
 */
@Serializable
data class AppPreferences(
    val themeMode: String = "system",           // system | light | dark
    val dynamicColorEnabled: Boolean = true,
    val fontSize: Int = 14,
    val lineWrap: Boolean = false,
    val tabSize: Int = 4,
    val autoSave: Boolean = true,
    val autoSaveIntervalMs: Long = 5_000L,
    val defaultProviderId: String? = null,
    val defaultModelId: String? = null,
    val firstLaunch: Boolean = true,
    val editorFontLigatures: Boolean = true,
    val editorShowLineNumbers: Boolean = true,
    val editorShowWhitespace: Boolean = false,
    val aiAutoComplete: Boolean = true,
    val aiAutoCompleteMode: String = "auto",      // auto | manual
    val aiStreamResponse: Boolean = true,
    val aiShowReasoning: Boolean = true,
    val aiShowTokenUsage: Boolean = true,
    val aiMaxRetries: Int = 3,
    val terminalShell: String = "sh",             // sh | bash | termux
    val budgetDailyTokenLimit: Long? = null,       // 聚合网关：每日 token 上限（null=不限）
    val budgetMonthlyCostUsd: Double? = null,      // 聚合网关：每月预算上限 USD（null=不限）
    val lastOpenedProjectId: String? = null,
    val lastOpenedFilePath: String? = null
)