package com.skyauto.app.data.session

import android.content.Context
import android.content.SharedPreferences
import com.skyauto.app.data.model.User
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 登录态管理器：内存态 + 本地持久化。
 * 登录后将 [User] 写入 SharedPreferences，重建进程后立即恢复登录态（无需等网络），
 * 再由 [com.skyauto.app.ui.viewmodel.AuthViewModel] 调用 `me()` 校验/刷新。
 */
@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("skyauto_session", Context.MODE_PRIVATE)

    private val json = Json { ignoreUnknownKeys = true }

    /** 是否已持久化登录态（本地恢复用）。 */
    val hasSession: Boolean
        get() = prefs.contains(KEY_USER)

    private val _currentUser = MutableStateFlow<User?>(restoreUser())
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(_currentUser.value != null)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    fun onLogin(user: User) {
        _currentUser.value = user
        _isLoggedIn.value = true
        persistUser(user)
    }

    fun onLogout() {
        _currentUser.value = null
        _isLoggedIn.value = false
        prefs.edit().remove(KEY_USER).apply()
    }

    private fun persistUser(user: User) {
        runCatching { prefs.edit().putString(KEY_USER, json.encodeToString(User.serializer(), user)).apply() }
    }

    private fun restoreUser(): User? {
        val raw = prefs.getString(KEY_USER, null) ?: return null
        return runCatching { json.decodeFromString(User.serializer(), raw) }.getOrNull()
    }

    private companion object {
        const val KEY_USER = "logged_in_user"
    }
}