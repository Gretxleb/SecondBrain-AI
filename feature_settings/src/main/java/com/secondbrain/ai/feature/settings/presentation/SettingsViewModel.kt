package com.secondbrain.ai.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secondbrain.ai.domain.usecase.SettingsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsUseCases: SettingsUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState

    init {
        viewModelScope.launch {
            settingsUseCases.settingsStream().collect { settings ->
                _uiState.update { it.copy(
                    darkModeEnabled = settings.darkModeEnabled,
                    language = settings.language,
                    biometricEnabled = settings.biometricEnabled,
                    syncEnabled = settings.syncEnabled
                ) }
            }
        }
    }

    fun onDarkModeToggled(enabled: Boolean) {
        _uiState.update { it.copy(darkModeEnabled = enabled) }
    }

    fun onBiometricToggled(enabled: Boolean) {
        _uiState.update { it.copy(biometricEnabled = enabled) }
    }

    fun onSyncToggled(enabled: Boolean) {
        _uiState.update { it.copy(syncEnabled = enabled) }
    }

    fun onLanguageChanged(language: String) {
        _uiState.update { it.copy(language = language) }
    }

    fun onSaveSettings() {
        viewModelScope.launch {
            settingsUseCases.updateSettings(_uiState.value.toSettingsState())
        }
    }
}

private fun SettingsUiState.toSettingsState() = com.secondbrain.ai.domain.model.SettingsState(
    darkModeEnabled = darkModeEnabled,
    language = language,
    biometricEnabled = biometricEnabled,
    syncEnabled = syncEnabled
)
