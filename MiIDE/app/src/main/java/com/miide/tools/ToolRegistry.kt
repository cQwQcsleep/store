package com.miide.tools

import java.util.concurrent.ConcurrentHashMap

/**
 * 工具注册表：管理所有可被 AI 调用的工具（function calling）。
 *
 * - 内置工具启动时注册；
 * - MCP 服务器连接后，其工具按 `serverName__toolName` 注册；
 * - 支持运行时增删（插件市场 / MCP 配置变更）。
 */
class ToolRegistry {

    private val tools = ConcurrentHashMap<String, AgentTool>()

    /** 注册工具；同名覆盖。 */
    fun register(tool: AgentTool) {
        tools[tool.name] = tool
    }

    /** 注销工具。 */
    fun unregister(name: String) {
        tools.remove(name)
    }

    /** 按名称查找。 */
    fun find(name: String): AgentTool? = tools[name]

    /** 当前全部工具。 */
    fun all(): List<AgentTool> = tools.values.toList()

    /** 清空（用于 MCP 全量重连等场景）。 */
    fun clear() {
        tools.clear()
    }
}
