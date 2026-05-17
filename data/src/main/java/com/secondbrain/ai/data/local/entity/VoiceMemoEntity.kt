package com.secondbrain.ai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "voice_memos")
data class VoiceMemoEntity(
    @PrimaryKey val id: String,
    val noteId: String,
    val transcript: String,
    val audioUrl: String,
    val createdAt: Long
)
