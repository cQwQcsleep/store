package com.miide.ui.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miide.core.runtime.CodeRuntime
import com.miide.core.runtime.RunResult
import com.miide.core.runtime.RuntimeRegistry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/** 一次运行的 UI 状态。 */
data class RunUiState(
    val running: Boolean = false,
    val language: String? = null,
    val result: RunResult? = null,
    val error: String? = null
)

/** 一键运行：通过 [RuntimeRegistry] 执行代码并展示结果。 */
@HiltViewModel
class RunViewModel @Inject constructor(
    private val runtimeRegistry: RuntimeRegistry
) : ViewModel() {

    private val _state = MutableStateFlow(RunUiState())
    val state: StateFlow<RunUiState> = _state.asStateFlow()

    /** 当前文件可用的运行时（按扩展名自动匹配，无匹配时返回 null）。 */
    fun runtimeFor(fileName: String): CodeRuntime? {
        val ext = fileName.substringAfterLast('.', "").lowercase()
        return if (ext.isBlank()) null else runtimeRegistry.findByExtension(".$ext")
    }

    fun run(code: String, fileName: String) {
        val runtime = runtimeFor(fileName)
        if (runtime == null) {
            _state.value = RunUiState(
                error = "暂不支持 ${fileName.substringAfterLast('.', "此")} 文件。支持：${runtimeRegistry.available().joinToString(", ") { it.id }}"
            )
            return
        }
        if (_state.value.running) return

        _state.value = RunUiState(running = true, language = runtime.id)
        viewModelScope.launch {
            val result = runtime.execute(code)
            _state.value = RunUiState(running = false, language = runtime.id, result = result)
        }
    }

    fun dismiss() {
        _state.value = RunUiState()
    }
}
