package com.secondbrain.ai.domain.model

data class SettingsState(
    val darkModeEnabled: Boolean,
    val language: String,
    val biometricEnabled: Boolean,
    val syncEnabled: Boolean
)
