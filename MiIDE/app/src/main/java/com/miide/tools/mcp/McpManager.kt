package com.miide.tools.mcp

import com.miide.tools.ToolRegistry
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.Json

/**
 * MCP 服务器管理器：
 * - 连接 / 断开服务器；
 * - 把服务器暴露的工具注册进 [ToolRegistry]（供 function calling 使用）；
 * - 同一服务器可多工具、多会话。
 */
class McpManager(
    private val http: HttpClient,
    private val registry: ToolRegistry,
    private val json: Json = Json { ignoreUnknownKeys = true }
) {

    private data class ServerState(
        val config: McpServerConfig,
        val client: McpClient,
        val toolNames: MutableList<String> = mutableListOf()
    )

    private val mutex = Mutex()
    private val servers = LinkedHashMap<String, ServerState>()

    private val _connectedIds = MutableStateFlow<Set<String>>(emptySet())

    /** 已连接服务器 id 集合（供 UI 显示状态徽标）。 */
    fun connectedIdsFlow(): kotlinx.coroutines.flow.StateFlow<Set<String>> = _connectedIds.asStateFlow()

    private fun refreshConnectedIds() {
        _connectedIds.value = servers.keys.toSet()
    }

    /** 当前已连接的服务器配置。 */
    suspend fun connectedServers(): List<McpServerConfig> = mutex.withLock {
        servers.values.map { it.config }
    }

    /** 连接并注册工具；失败抛出异常（由调用方展示）。 */
    suspend fun connect(config: McpServerConfig) {
        mutex.withLock { connectLocked(config) }
    }

    /** 断开并注销工具。 */
    suspend fun disconnect(id: String) {
        mutex.withLock { disconnectLocked(id) }
    }

    /** 按配置全量重连（配置变更 / 启动加载后调用）。失败静默跳过，不影响其他服务器。 */
    suspend fun sync(configs: List<McpServerConfig>) {
        mutex.withLock {
            val wanted = configs.filter { it.enabled }.map { it.id }.toSet()
            servers.keys.filterNot { it in wanted }.forEach { disconnectLocked(it) }
            val byId = configs.filter { it.enabled }.associateBy { it.id }
            for ((id, cfg) in byId) {
                val existing = servers[id]
                if (existing == null || existing.config != cfg) {
                    try {
                        connectLocked(cfg)
                    } catch (_: Exception) {
                        // 单个服务器失败不影响其他；错误由上层展示
                    }
                }
            }
        }
    }

    private suspend fun connectLocked(config: McpServerConfig) {
        disconnectLocked(config.id)
        val client = McpClient(http, config, json)
        client.connect()
        val tools = client.listTools()
        val state = ServerState(config, client)
        val serverName = config.name.ifBlank { config.id }
        for (info in tools) {
            val tool = McpTool(client, serverName, info)
            registry.register(tool)
            state.toolNames.add(tool.name)
        }
        servers[config.id] = state
        refreshConnectedIds()
    }

    private suspend fun disconnectLocked(id: String) {
        val state = servers.remove(id) ?: return
        state.toolNames.forEach { registry.unregister(it) }
        state.client.disconnect()
        refreshConnectedIds()
    }
}
