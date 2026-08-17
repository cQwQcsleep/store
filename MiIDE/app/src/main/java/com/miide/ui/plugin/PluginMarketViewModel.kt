package com.miide.ui.plugin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miide.core.data.repository.PluginRepository
import com.miide.core.plugin.PluginCatalog
import com.miide.core.plugin.PluginPackage
import com.miide.core.plugin.PluginRunResult
import com.miide.core.plugin.PluginSandbox
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PluginMarketUiState(
    val busy: Boolean = false,
    val message: String? = null,
    /** 运行脚本插件的输出。 */
    val runResult: PluginRunResult? = null,
    val runningPluginId: String? = null
)

@HiltViewModel
class PluginMarketViewModel @Inject constructor(
    private val pluginRepository: PluginRepository,
    private val pluginSandbox: PluginSandbox
) : ViewModel() {

    /** 市场目录（离线内置）。 */
    val catalog: List<PluginPackage> = PluginCatalog.packages

    /** 已安装插件，按 id 建立索引便于查询。 */
    val installed: StateFlow<Map<String, com.miide.core.plugin.InstalledPlugin>> =
        pluginRepository.observeInstalled()
            .map { list -> list.associateBy { it.manifest.id } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyMap())

    private val _state = MutableStateFlow(PluginMarketUiState())
    val state: StateFlow<PluginMarketUiState> = _state.asStateFlow()

    fun install(pkg: PluginPackage) {
        viewModelScope.launch {
            _state.value = _state.value.copy(busy = true, message = null)
            pluginRepository.install(pkg)
            _state.value = _state.value.copy(
                busy = false,
                message = "已安装「${pkg.manifest.name}」v${pkg.manifest.version}"
            )
        }
    }

    fun uninstall(id: String, name: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(busy = true, message = null)
            pluginRepository.uninstall(id)
            _state.value = _state.value.copy(busy = false, message = "已卸载「$name」")
        }
    }

    fun setEnabled(id: String, enabled: Boolean) {
        viewModelScope.launch { pluginRepository.setEnabled(id, enabled) }
    }

    /** 运行脚本插件（仅 SCRIPT 类型且已启用）。 */
    fun runScript(manifest: com.miide.core.plugin.PluginManifest) {
        if (manifest.type != com.miide.core.plugin.PluginType.SCRIPT || manifest.script.isBlank()) return
        viewModelScope.launch {
            _state.value = _state.value.copy(runningPluginId = manifest.id, runResult = null, message = null)
            val result = pluginSandbox.execute(manifest.script)
            _state.value = _state.value.copy(runningPluginId = null, runResult = result)
        }
    }

    fun clearMessage() {
        _state.value = _state.value.copy(message = null)
    }
}
