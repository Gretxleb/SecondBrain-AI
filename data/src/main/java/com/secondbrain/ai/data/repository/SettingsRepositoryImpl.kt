package com.secondbrain.ai.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.secondbrain.ai.domain.model.SettingsState
import com.secondbrain.ai.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val settingsDataStore: DataStore<androidx.datastore.preferences.core.Preferences>
) : SettingsRepository {

    private object Keys {
        val DARK_MODE = booleanPreferencesKey("dark_mode_enabled")
        val LANGUAGE = stringPreferencesKey("language")
        val BIOMETRIC = booleanPreferencesKey("biometric_enabled")
        val SYNC = booleanPreferencesKey("sync_enabled")
    }

    override fun settingsStream(): Flow<SettingsState> {
        return settingsDataStore.data.map { preferences ->
            SettingsState(
                darkModeEnabled = preferences[Keys.DARK_MODE] ?: false,
                language = preferences[Keys.LANGUAGE] ?: "en",
                biometricEnabled = preferences[Keys.BIOMETRIC] ?: false,
                syncEnabled = preferences[Keys.SYNC] ?: true
            )
        }
    }

    override suspend fun updateSettings(settingsState: SettingsState): Result<Unit> {
        return try {
            settingsDataStore.edit { preferences ->
                preferences[Keys.DARK_MODE] = settingsState.darkModeEnabled
                preferences[Keys.LANGUAGE] = settingsState.language
                preferences[Keys.BIOMETRIC] = settingsState.biometricEnabled
                preferences[Keys.SYNC] = settingsState.syncEnabled
            }
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
