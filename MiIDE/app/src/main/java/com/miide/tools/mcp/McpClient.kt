package com.miide.tools.mcp

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsChannel
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.utils.io.readUTF8Line
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.put

/**
 * MCP 客户端：基于 JSON-RPC 2.0 与服务器通信。
 *
 * 支持两种传输：
 * - HTTP（streamable HTTP）：单端点 POST，响应可为 JSON 或 text/event-stream；
 * - SSE：GET 事件流发现消息端点，再 POST 请求。
 *
 * 覆盖 MCP 核心能力：initialize / notifications/initialized / tools/list / tools/call。
 */
class McpClient(
    private val http: HttpClient,
    private val config: McpServerConfig,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {

    private val mutex = Mutex()
    private var seq = 0
    private var connected = false

    val isConnected: Boolean get() = connected

    /** 服务器返回的工具信息。 */
    @Serializable
    data class ToolInfo(
        val name: String,
        val description: String = "",
        val inputSchema: JsonElement? = null
    )

    /** 建立连接（initialize 握手）。失败抛出异常。 */
    suspend fun connect() {
        mutex.withLock {
            if (connected) return
            val params = buildJsonObject {
                put("protocolVersion", PROTOCOL_VERSION)
                put("capabilities", JsonObject(emptyMap()))
                put("clientInfo", buildJsonObject {
                    put("name", "MiIDE")
                    put("version", CLIENT_VERSION)
                })
            }
            val response = request("initialize", params)
            // 通知服务器客户端已就绪（notification，无响应）
            sendNotification("notifications/initialized", JsonObject(emptyMap()))
            connected = true
        }
    }

    suspend fun disconnect() {
        mutex.withLock { connected = false }
    }

    /** 拉取服务器提供的工具列表。 */
    suspend fun listTools(): List<ToolInfo> {
        connect()
        val result = request("tools/list", JsonObject(emptyMap()))
        val tools = result["tools"] as? kotlinx.serialization.json.JsonArray ?: return emptyList()
        return tools.mapNotNull { el ->
            val obj = el as? JsonObject ?: return@mapNotNull null
            val name = (obj["name"] as? JsonPrimitive)?.content ?: return@mapNotNull null
            ToolInfo(
                name = name,
                description = (obj["description"] as? JsonPrimitive)?.content.orEmpty(),
                inputSchema = obj["inputSchema"]
            )
        }
    }

    /** 调用服务器工具；返回工具结果文本（含 isError 标记）。 */
    suspend fun callTool(name: String, arguments: JsonObject): McpToolResult {
        connect()
        val params = buildJsonObject {
            put("name", name)
            put("arguments", arguments)
        }
        val result = request("tools/call", params)
        return parseToolResult(result)
    }

    private fun parseToolResult(result: JsonObject): McpToolResult {
        val isError = (result["isError"] as? JsonPrimitive)?.booleanOrNull ?: false
        val content = result["content"] as? kotlinx.serialization.json.JsonArray ?: kotlinx.serialization.json.JsonArray(emptyList())
        val text = buildString {
            for (item in content) {
                val obj = item as? JsonObject ?: continue
                val type = (obj["type"] as? JsonPrimitive)?.content
                when (type) {
                    "text" -> append((obj["text"] as? JsonPrimitive)?.content.orEmpty())
                    "image" -> append("[图片]")
                    "resource" -> append((obj["resource"] as? JsonPrimitive)?.content ?: "[资源]")
                    else -> append(obj.toString())
                }
                append('\n')
            }
        }.trim()
        return McpToolResult(
            success = !isError,
            text = text.ifBlank { if (isError) "（工具返回空结果）" else "（无内容）" }
        )
    }

    /** 发送 JSON-RPC 请求并等待响应（result 或 error）。 */
    private suspend fun request(method: String, params: JsonObject): JsonObject {
        val id = nextId()
        val payload = buildJsonObject {
            put("jsonrpc", "2.0")
            put("id", id)
            put("method", method)
            put("params", params)
        }
        val response = post(payload.toString(), stream = false)
        val body = response["body"] as? JsonObject ?: JsonObject(emptyMap())
        (body["error"] as? JsonObject)?.let { err ->
            val message = (err["message"] as? JsonPrimitive)?.content ?: "JSON-RPC 错误"
            throw McpException("MCP 请求 $method 失败：$message")
        }
        return body["result"] as? JsonObject ?: JsonObject(emptyMap())
    }

    private suspend fun sendNotification(method: String, params: JsonObject) {
        val payload = buildJsonObject {
            put("jsonrpc", "2.0")
            put("method", method)
            put("params", params)
        }
        // 通知无 id，服务器通常无响应
        post(payload.toString(), stream = false)
    }

    /** POST JSON 请求；返回 {"body": ...}。stream=true 时按 SSE 解析。 */
    private suspend fun post(body: String, stream: Boolean): JsonObject {
        return when (config.transport) {
            McpTransport.HTTP -> httpPost(config.endpoint, body)
            McpTransport.SSE -> {
                // 先确保消息端点已就绪（由事件流获得）
                val msgUrl = ensureMessageEndpoint()
                httpPost(msgUrl, body)
            }
            McpTransport.STDIO -> throw McpException("STDIO 传输暂未支持（需 Termux 环境）")
        }
    }

    private var messageEndpoint: String? = null

    private suspend fun ensureMessageEndpoint(): String {
        messageEndpoint?.let { return it }
        val url = http.get(config.endpoint) {
            header(HttpHeaders.Accept, "text/event-stream")
            config.headers.forEach { (k, v) -> header(k, v) }
        }.let { resp ->
            if (!resp.status.isSuccess()) throw McpException("MCP 事件流连接失败：HTTP ${resp.status.value}")
            parseEventStreamEndpoint(resp.bodyAsChannel())
        }
        messageEndpoint = url
        return url
    }

    /** 从 SSE 事件流中解析 MCP 端点（`endpoint` 事件）。 */
    private suspend fun parseEventStreamEndpoint(channel: io.ktor.utils.io.ByteReadChannel): String {
        while (!channel.isClosedForRead) {
            val line = channel.readUTF8Line() ?: break
            if (line.startsWith("data:")) {
                val data = line.removePrefix("data:").trim()
                if (data.isBlank()) continue
                val event = runCatching { json.parseToJsonElement(data).jsonObject }.getOrNull() ?: continue
                (event["endpoint"] as? JsonPrimitive)?.content?.let { return it }
            }
        }
        throw McpException("MCP 事件流未提供 endpoint")
    }

    private suspend fun httpPost(url: String, body: String): JsonObject {
        val response = http.post(url) {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.Accept, "application/json, text/event-stream")
            header(MCP_PROTOCOL_HEADER, PROTOCOL_VERSION)
            config.headers.forEach { (k, v) -> header(k, v) }
            setBody(body)
        }
        if (!response.status.isSuccess()) {
            val err = runCatching { response.bodyAsText() }.getOrDefault("").take(200)
            throw McpException("MCP HTTP ${response.status.value}: $err")
        }
        val contentType = response.headers[HttpHeaders.ContentType].orEmpty()
        val raw = response.bodyAsText()
        val jsonBody = if (contentType.contains("text/event-stream") || raw.contains("\ndata:")) {
            // SSE 单次响应：取第一条 data
            raw.lineSequence()
                .filter { it.startsWith("data:") }
                .mapNotNull { line -> line.removePrefix("data:").trim().takeIf { it.isNotBlank() } }
                .firstOrNull()
        } else {
            raw
        }
        val element = runCatching { json.parseToJsonElement(jsonBody ?: raw) }.getOrNull()
        return buildJsonObject {
            put("body", element ?: JsonObject(emptyMap()))
        }
    }

    private fun nextId(): Int = ++seq

    companion object {
        const val PROTOCOL_VERSION = "2025-06-18"
        const val CLIENT_VERSION = "0.1.0"
        const val MCP_PROTOCOL_HEADER = "MCP-Protocol-Version"
    }
}

/** MCP 工具调用结果。 */
data class McpToolResult(
    val success: Boolean,
    val text: String
)

class McpException(message: String) : Exception(message)
