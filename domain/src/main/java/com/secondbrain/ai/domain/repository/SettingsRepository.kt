package com.secondbrain.ai.domain.repository

import com.secondbrain.ai.domain.model.SettingsState
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun settingsStream(): Flow<SettingsState>
    suspend fun updateSettings(settingsState: SettingsState): Result<Unit>
}
