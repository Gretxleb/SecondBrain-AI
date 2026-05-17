package com.secondbrain.ai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity(
    @PrimaryKey val id: String,
    val noteId: String,
    val title: String,
    val description: String,
    val time: Long,
    val active: Boolean
)
