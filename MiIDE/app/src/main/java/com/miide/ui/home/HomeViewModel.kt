package com.miide.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miide.core.data.repository.ProviderRepository
import com.miide.core.data.settings.PreferencesManager
import com.miide.core.model.ProviderConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/** 首页状态。 */
data class HomeUiState(
    val providers: List<ProviderConfig> = emptyList(),
    val defaultProviderId: String? = null
) {
    val enabledCount: Int get() = providers.count { it.enabled }
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    providerRepository: ProviderRepository,
    preferencesManager: PreferencesManager
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = combine(
        providerRepository.observeAll(),
        preferencesManager.preferences
    ) { providers, prefs ->
        HomeUiState(
            providers = providers,
            defaultProviderId = prefs.defaultProviderId
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HomeUiState())
}
