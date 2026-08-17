package com.miide.tools.mcp

import kotlinx.serialization.Serializable

/** MCP 传输方式。 */
@Serializable
enum class McpTransport {
    /** HTTP（streamable HTTP：POST JSON 或 text/event-stream）。 */
    HTTP,

    /** 传统 SSE（GET 事件流 + POST 消息端点）。 */
    SSE,

    /** STDIO（需 Termux 等本地进程环境，暂作占位）。 */
    STDIO
}

/**
 * MCP 服务器配置（可持久化到偏好设置）。
 *
 * @param endpoint 服务器地址：
 *   - HTTP：单端点（streamable HTTP 的 POST 地址）；
 *   - SSE：事件流 GET 地址。
 */
@Serializable
data class McpServerConfig(
    val id: String = "",
    val name: String = "",
    val transport: McpTransport = McpTransport.HTTP,
    val endpoint: String = "",
    /** 认证 / 自定义请求头，如 "Authorization" to "Bearer xxx"。 */
    val headers: Map<String, String> = emptyMap(),
    val enabled: Boolean = true
)
