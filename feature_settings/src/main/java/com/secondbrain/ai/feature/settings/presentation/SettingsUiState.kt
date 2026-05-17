package com.secondbrain.ai.feature.settings.presentation

data class SettingsUiState(
    val darkModeEnabled: Boolean = false,
    val language: String = "en",
    val biometricEnabled: Boolean = false,
    val syncEnabled: Boolean = true
)
