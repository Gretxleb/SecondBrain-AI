package com.secondbrain.ai.data.local.database

import com.secondbrain.ai.data.local.entity.NoteEntity
import com.secondbrain.ai.data.local.entity.ReminderEntity
import com.secondbrain.ai.data.local.entity.SettingsEntity
import com.secondbrain.ai.data.local.entity.VoiceMemoEntity
import com.secondbrain.ai.domain.model.Note
import com.secondbrain.ai.domain.model.Reminder
import com.secondbrain.ai.domain.model.SettingsState
import com.secondbrain.ai.domain.model.VoiceMemo
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal fun NoteEntity.toDomain(): Note = Note(
    id = id,
    title = title,
    content = content,
    tags = Json.decodeFromString(tagsJson),
    category = category,
    pinned = pinned,
    archived = archived,
    createdAt = createdAt,
    updatedAt = updatedAt
)

internal fun Note.toEntity(): NoteEntity = NoteEntity(
    id = id,
    title = title,
    content = content,
    tagsJson = Json.encodeToString(tags),
    category = category,
    pinned = pinned,
    archived = archived,
    createdAt = createdAt,
    updatedAt = updatedAt
)

internal fun SettingsEntity.toDomain(): SettingsState = SettingsState(
    darkModeEnabled = darkModeEnabled,
    language = language,
    biometricEnabled = biometricEnabled,
    syncEnabled = syncEnabled
)

internal fun SettingsState.toEntity(): SettingsEntity = SettingsEntity(
    darkModeEnabled = darkModeEnabled,
    language = language,
    biometricEnabled = biometricEnabled,
    syncEnabled = syncEnabled
)

internal fun ReminderEntity.toDomain(): Reminder = Reminder(
    id = id,
    noteId = noteId,
    title = title,
    description = description,
    time = time,
    active = active
)

internal fun Reminder.toEntity(): ReminderEntity = ReminderEntity(
    id = id,
    noteId = noteId,
    title = title,
    description = description,
    time = time,
    active = active
)

internal fun VoiceMemoEntity.toDomain(): VoiceMemo = VoiceMemo(
    id = id,
    noteId = noteId,
    transcript = transcript,
    audioUrl = audioUrl,
    createdAt = createdAt
)

internal fun VoiceMemo.toEntity(): VoiceMemoEntity = VoiceMemoEntity(
    id = id,
    noteId = noteId,
    transcript = transcript,
    audioUrl = audioUrl,
    createdAt = createdAt
)
