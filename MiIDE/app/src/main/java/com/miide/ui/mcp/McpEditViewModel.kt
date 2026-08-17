package com.miide.ui.mcp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miide.core.data.settings.PreferencesManager
import com.miide.tools.mcp.McpManager
import com.miide.tools.mcp.McpServerConfig
import com.miide.tools.mcp.McpTransport
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import java.util.UUID
import javax.inject.Inject

/** 单个 MCP 服务器编辑状态。 */
data class TestOutcome(val success: Boolean, val message: String)

@HiltViewModel
class McpEditViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    private val mcpManager: McpManager,
    private val json: Json
) : ViewModel() {

    var name: String = ""
    var transport: McpTransport = McpTransport.HTTP
    var endpoint: String = ""
    var headersText: String = ""
    var enabled: Boolean = true

    var testing: Boolean = false
        private set
    var saving: Boolean = false
        private set
    var testOutcome: TestOutcome? = null
        private set
    var errorMessage: String? = null
        private set

    private var editingId: String? = null

    fun load(serverId: String?) {
        if (serverId == null) return
        viewModelScope.launch {
            val list = readList()
            list.firstOrNull { it.id == serverId }?.let { s ->
                editingId = s.id
                name = s.name
                transport = s.transport
                endpoint = s.endpoint
                headersText = s.headers.entries.joinToString("\n") { (k, v) -> "$k: $v" }
                enabled = s.enabled
            }
        }
    }

    fun testConnection() {
        val config = buildConfig()
        if (config.endpoint.isBlank()) {
            testOutcome = TestOutcome(false, "请先填写服务器地址")
            return
        }
        viewModelScope.launch {
            testing = true
            testOutcome = null
            errorMessage = null
            testOutcome = try {
                mcpManager.connect(config)
                TestOutcome(true, "连接成功，工具已注册")
            } catch (e: Exception) {
                TestOutcome(false, "连接失败：${e.message ?: e::class.simpleName}")
            }
            testing = false
        }
    }

    fun save(onSaved: () -> Unit) {
        if (name.isBlank() || endpoint.isBlank()) {
            errorMessage = "请填写服务器名称与地址"
            return
        }
        viewModelScope.launch {
            saving = true
            errorMessage = null
            val config = buildConfig()
            val list = readList()
            val updated = if (editingId != null) {
                list.map { if (it.id == editingId) config else it }
            } else {
                list + config
            }
            preferencesManager.setMcpServers(encode(updated))
            saving = false
            onSaved()
        }
    }

    private fun buildConfig(): McpServerConfig {
        val id = editingId ?: UUID.randomUUID().toString()
        val headers = headersText.lineSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .mapNotNull { line ->
                val idx = line.indexOf(':')
                if (idx <= 0) null
                else line.substring(0, idx).trim() to line.substring(idx + 1).trim()
            }
            .toMap()
        return McpServerConfig(
            id = id,
            name = name.trim(),
            transport = transport,
            endpoint = endpoint.trim(),
            headers = headers,
            enabled = enabled
        )
    }

    private suspend fun readList(): List<McpServerConfig> {
        val raw = preferencesManager.observeMcpServers().first()
        return raw?.takeIf { it.isNotBlank() }?.let {
            runCatching {
                json.decodeFromString(ListSerializer(McpServerConfig.serializer()), it)
            }.getOrDefault(emptyList())
        } ?: emptyList()
    }

    private fun encode(configs: List<McpServerConfig>): String =
        json.encodeToString(ListSerializer(McpServerConfig.serializer()), configs)
}
