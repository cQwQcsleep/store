package com.miide.core.plugin

import kotlinx.serialization.Serializable

/** 插件类型。 */
enum class PluginType {
    /** 脚本插件：QuickJS 沙箱中运行的 JS 脚本。 */
    SCRIPT,

    /** 主题包：品牌化配色 / 视觉调整（基础版仅登记占位）。 */
    THEME,

    /** 官方功能包：标记某能力的开关（Git / 远程 / 运行时等）。 */
    FEATURE
}

/**
 * 插件清单（发布 / 安装 / 展示的元数据）。
 *
 * 基础版插件的脚本源码直接内嵌在 [script] 字段中，随清单一起安装。
 */
@Serializable
data class PluginManifest(
    val id: String,
    val name: String,
    val version: String,
    val description: String = "",
    val author: String = "MiIDE 社区",
    val type: PluginType = PluginType.SCRIPT,
    /** 脚本插件入口文件名（如 main.js），用于展示与执行。 */
    val entry: String? = null,
    /** 脚本插件内嵌源码。 */
    val script: String = "",
    /** 声明所需权限（基础版仅记录与展示，不实际授予系统能力）。 */
    val permissions: List<String> = emptyList(),
    val icon: String = "",
    val homepage: String = ""
)

/** 可分发 / 安装的插件包：清单 + 内嵌脚本。 */
@Serializable
data class PluginPackage(
    val manifest: PluginManifest,
    /** 安装时持久化的脚本内容（与 manifest.script 一致）。 */
    val code: String = manifest.script
)

/** 脚本插件运行结果。 */
data class PluginRunResult(
    val exitCode: Int,
    val stdout: String,
    val stderr: String,
    /** 插件通过 MiIDE.emit() 产生的动作事件（供过程可见展示）。 */
    val emits: List<String>,
    val durationMs: Long
)

/** 已安装插件（含启用状态）。 */
data class InstalledPlugin(
    val manifest: PluginManifest,
    val enabled: Boolean = true,
    val installedAt: Long = System.currentTimeMillis()
)
