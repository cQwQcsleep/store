package com.miide.ui.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miide.core.data.repository.RemoteRepository
import com.miide.core.remote.RemoteProfile
import com.miide.core.remote.RemoteService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RemoteUiState(
    val profiles: List<RemoteProfile> = emptyList(),
    val connectedProfile: RemoteProfile? = null,
    val entries: List<com.miide.core.remote.RemoteEntry> = emptyList(),
    val currentPath: String = "/",
    val fileContent: String? = null,
    val fileNameHint: String = "",
    val filePathHint: String = "",
    val execResult: String? = null,
    val loading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class RemoteViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val remoteService: RemoteService
) : ViewModel() {

    val profiles: StateFlow<List<RemoteProfile>> = remoteRepository.observeAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _state = MutableStateFlow(RemoteUiState())
    val state: StateFlow<RemoteUiState> = _state.asStateFlow()

    fun deleteProfile(profile: RemoteProfile) {
        viewModelScope.launch { remoteRepository.deleteById(profile.id) }
    }

    fun upsertProfile(profile: RemoteProfile) {
        viewModelScope.launch { remoteRepository.upsert(profile) }
    }

    fun connect(profile: RemoteProfile) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null, connectedProfile = profile)
            when (val result = remoteService.list(profile, profile.basePath)) {
                is com.miide.core.remote.RemoteResult.Success -> {
                    _state.value = _state.value.copy(
                        entries = result.data,
                        currentPath = profile.basePath,
                        fileContent = null,
                        execResult = null,
                        loading = false
                    )
                }
                is com.miide.core.remote.RemoteResult.Failure -> {
                    _state.value = _state.value.copy(
                        loading = false,
                        error = "连接失败: ${result.message}",
                        connectedProfile = null
                    )
                }
            }
        }
    }

    fun disconnect() {
        _state.value = RemoteUiState(profiles = _state.value.profiles)
    }

    fun navigateToDir(path: String) {
        val profile = _state.value.connectedProfile ?: return
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            when (val result = remoteService.list(profile, path)) {
                is com.miide.core.remote.RemoteResult.Success -> {
                    _state.value = _state.value.copy(
                        entries = result.data,
                        currentPath = path,
                        fileContent = null,
                        loading = false
                    )
                }
                is com.miide.core.remote.RemoteResult.Failure -> {
                    _state.value = _state.value.copy(loading = false, error = "读取失败: ${result.message}")
                }
            }
        }
    }

    fun readFile(path: String) {
        val profile = _state.value.connectedProfile ?: return
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            when (val result = remoteService.read(profile, path)) {
                is com.miide.core.remote.RemoteResult.Success -> {
                    _state.value = _state.value.copy(
                        fileContent = result.data,
                        fileNameHint = path.substringAfterLast('/'),
                        filePathHint = path,
                        execResult = null,
                        loading = false
                    )
                }
                is com.miide.core.remote.RemoteResult.Failure -> {
                    _state.value = _state.value.copy(loading = false, error = "读取失败: ${result.message}")
                }
            }
        }
    }

    fun execCommand(command: String) {
        val profile = _state.value.connectedProfile ?: return
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)
            when (val result = remoteService.exec(profile, command)) {
                is com.miide.core.remote.RemoteResult.Success -> {
                    _state.value = _state.value.copy(
                        execResult = result.data,
                        fileContent = null,
                        loading = false
                    )
                }
                is com.miide.core.remote.RemoteResult.Failure -> {
                    _state.value = _state.value.copy(loading = false, error = "执行失败: ${result.message}")
                }
            }
        }
    }

    /**
     * 将编辑器绑定的远程文件内容写回远程主机。
     * 无远程绑定返回 null（此时编辑器走本地 SAF 保存）；失败返回 (false, 错误信息)。
     */
    suspend fun saveActiveRemote(content: String): Pair<Boolean, String>? {
        val active = RemoteOpenBridge.active ?: return null
        val profile = remoteRepository.getById(active.profileId) ?: return false to "找不到远程主机"
        return when (val result = remoteService.write(profile, active.remotePath, content)) {
            is com.miide.core.remote.RemoteResult.Success -> true to "已保存到远程 ${active.remotePath}"
            is com.miide.core.remote.RemoteResult.Failure -> false to "远程保存失败: ${result.message}"
        }
    }
}