package com.miide.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 已安装插件实体。manifestJson 序列化完整 [com.miide.core.plugin.PluginManifest]，
 * script 单独持久化便于读取执行。
 */
@Entity(tableName = "plugins")
data class PluginEntity(
    @PrimaryKey val id: String,
    val name: String,
    val version: String,
    val description: String,
    val author: String,
    val type: String,
    val entry: String? = null,
    val script: String = "",
    val permissionsJson: String = "[]",
    val icon: String = "",
    val homepage: String = "",
    val enabled: Boolean = true,
    val installedAt: Long = System.currentTimeMillis()
)
