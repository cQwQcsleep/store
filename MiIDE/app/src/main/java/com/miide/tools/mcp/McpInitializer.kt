package com.miide.tools.mcp

import com.miide.core.data.settings.PreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * MCP 启动同步器：监听偏好设置中的服务器配置变化，自动连接 / 断开服务器并刷新工具注册表。
 *
 * 作为 @Singleton 惰性创建，注入到 Activity 触发实例化，随后在 App 生命周期内持续同步。
 */
@Singleton
class McpInitializer @Inject constructor(
    private val manager: McpManager,
    private val preferencesManager: PreferencesManager,
    private val json: Json
) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        scope.launch {
            preferencesManager.observeMcpServers().collectLatest { raw ->
                val configs = raw?.takeIf { it.isNotBlank() }?.let {
                    runCatching {
                        json.decodeFromString(ListSerializer(McpServerConfig.serializer()), it)
                    }.getOrDefault(emptyList())
                } ?: emptyList()
                manager.sync(configs)
            }
        }
    }
}
