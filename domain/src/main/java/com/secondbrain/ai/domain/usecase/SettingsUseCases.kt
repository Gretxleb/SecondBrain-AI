package com.secondbrain.ai.domain.usecase

import com.secondbrain.ai.domain.model.SettingsState
import com.secondbrain.ai.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsUseCases(
    private val repository: SettingsRepository
) {
    fun settingsStream(): Flow<SettingsState> = repository.settingsStream()
    suspend fun updateSettings(settingsState: SettingsState): Result<Unit> = repository.updateSettings(settingsState)
}
