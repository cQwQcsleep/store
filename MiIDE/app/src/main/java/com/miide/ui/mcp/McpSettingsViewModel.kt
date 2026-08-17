package com.miide.ui.mcp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miide.core.data.settings.PreferencesManager
import com.miide.tools.mcp.McpManager
import com.miide.tools.mcp.McpServerConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject

/** MCP 服务器列表管理。 */
@HiltViewModel
class McpSettingsViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val mcpManager: McpManager,
    private val json: Json
) : ViewModel() {

    val servers: StateFlow<List<McpServerConfig>> = preferencesManager.observeMcpServers()
        .map { raw -> decode(raw) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    /** 当前已连接的服务器 id（用于显示状态徽标）。 */
    val connectedIds: StateFlow<Set<String>> = mcpManager.connectedIdsFlow()

    fun setEnabled(config: McpServerConfig, enabled: Boolean) {
        val updated = servers.value.map { if (it.id == config.id) it.copy(enabled = enabled) else it }
        persist(updated)
    }

    fun delete(id: String) {
        viewModelScope.launch { mcpManager.disconnect(id) }
        persist(servers.value.filterNot { it.id == id })
    }

    private fun persist(configs: List<McpServerConfig>) {
        viewModelScope.launch {
            preferencesManager.setMcpServers(encode(configs))
        }
    }

    private fun decode(raw: String?): List<McpServerConfig> =
        raw?.takeIf { it.isNotBlank() }?.let {
            runCatching {
                json.decodeFromString(ListSerializer(McpServerConfig.serializer()), it)
            }.getOrDefault(emptyList())
        } ?: emptyList()

    private fun encode(configs: List<McpServerConfig>): String =
        json.encodeToString(ListSerializer(McpServerConfig.serializer()), configs)
}
