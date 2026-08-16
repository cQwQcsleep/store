package com.miide.ui.ai

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miide.core.data.repository.ProviderRepository
import com.miide.core.data.settings.PreferencesManager
import com.miide.core.model.ProviderConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiSettingsViewModel @Inject constructor(
    private val providerRepository: ProviderRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val providers: StateFlow<List<ProviderConfig>> = providerRepository.observeAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val defaultProviderId: StateFlow<String?> = preferencesManager.preferences
        .map { it.defaultProviderId }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    fun setDefault(id: String?) {
        viewModelScope.launch { preferencesManager.setDefaultProviderId(id) }
    }

    fun setEnabled(config: ProviderConfig, enabled: Boolean) {
        viewModelScope.launch { providerRepository.setEnabled(config.id, enabled) }
    }

    fun delete(config: ProviderConfig) {
        viewModelScope.launch {
            providerRepository.deleteById(config.id)
            if (defaultProviderId.value == config.id) setDefault(null)
        }
    }
}
