package com.skyauto.app.ui.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}

/** 简单的加载状态包装，供各 ViewModel 复用。 */
class UiHolder<T>(initial: T) {
    private val _state = MutableStateFlow(initial)
    val state: StateFlow<T> = _state.asStateFlow()

    val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    fun update(transform: (T) -> T) { _state.value = transform(_state.value) }
    fun setLoading(v: Boolean) { _loading.value = v }
    fun setError(msg: String?) { _error.value = msg }
    fun clearError() { _error.value = null }
}