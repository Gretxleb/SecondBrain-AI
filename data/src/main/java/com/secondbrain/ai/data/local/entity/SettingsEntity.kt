package com.secondbrain.ai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey val id: Int = 0,
    val darkModeEnabled: Boolean,
    val language: String,
    val biometricEnabled: Boolean,
    val syncEnabled: Boolean
)
