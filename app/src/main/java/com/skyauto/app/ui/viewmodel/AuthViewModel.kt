package com.skyauto.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyauto.app.data.model.User
import com.skyauto.app.data.repository.SkyRepository
import com.skyauto.app.data.session.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: SkyRepository,
    private val session: SessionManager
) : ViewModel() {

    val isLoggedIn: StateFlow<Boolean> = session.isLoggedIn
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val currentUser: StateFlow<User?> = session.currentUser
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _loginLoading = UiHolder(false)
    val loginLoading = _loginLoading.loading

    private val _error = UiHolder<String?>(null)
    val error = _error.error

    init {
        // 本地已持久化登录态时，用 me() 校验 Cookie 是否仍有效；失效则清除过期会话。
        // 校验失败（如网络异常）也回退到登录页，避免展示过期数据。
        if (session.hasSession) {
            viewModelScope.launch {
                if (repo.me().isFailure) session.onLogout()
            }
        }
    }

    fun login(email: String, password: String, onDone: (Boolean) -> Unit = {}) {
        _loginLoading.setLoading(true)
        _error.clearError()
        viewModelScope.launch {
            repo.login(email.trim(), password)
                .onSuccess { onDone(true) }
                .onFailure { _error.setError(it.message ?: "登录失败"); onDone(false) }
            _loginLoading.setLoading(false)
        }
    }

    fun register(email: String, username: String, password: String, onDone: (Boolean) -> Unit = {}) {
        _loginLoading.setLoading(true)
        _error.clearError()
        viewModelScope.launch {
            repo.register(email.trim(), username.trim(), password)
                .onSuccess { onDone(true) }
                .onFailure { _error.setError(it.message ?: "注册失败"); onDone(false) }
            _loginLoading.setLoading(false)
        }
    }

    fun sendResetCode(email: String) {
        _loginLoading.setLoading(true)
        _error.clearError()
        viewModelScope.launch {
            repo.sendResetCode(email.trim())
                .onFailure { _error.setError(it.message ?: "发送失败") }
                .onSuccess { _error.setError(null) }
            _loginLoading.setLoading(false)
        }
    }

    fun resetPassword(email: String, code: String, password: String, onDone: (Boolean) -> Unit) {
        _loginLoading.setLoading(true)
        _error.clearError()
        viewModelScope.launch {
            repo.resetPassword(email.trim(), code.trim(), password)
                .onSuccess { onDone(true) }
                .onFailure { _error.setError(it.message ?: "重置失败"); onDone(false) }
            _loginLoading.setLoading(false)
        }
    }

    fun logout() {
        viewModelScope.launch { repo.logout() }
    }
}