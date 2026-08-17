package com.miide.tools.mcp

import com.miide.tools.AgentTool
import kotlinx.serialization.json.JsonObject

/**
 * 将 MCP 服务器上的工具适配为 [AgentTool]。
 *
 * 命名规则：`serverName__toolName`，避免不同服务器工具重名冲突。
 */
class McpTool(
    private val client: McpClient,
    private val serverName: String,
    private val info: McpClient.ToolInfo,
) : AgentTool {

    override val name: String = "${serverName}__${info.name}"
    override val description: String = info.description
    override val parameters: String = info.inputSchema?.toString() ?: "{}"

    override suspend fun execute(arguments: JsonObject): String {
        val result = client.callTool(info.name, arguments)
        val prefix = if (result.success) "" else "[工具执行失败] "
        return prefix + result.text
    }
}
