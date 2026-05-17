package com.secondbrain.ai.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    @ColumnInfo(name = "tags") val tagsJson: String,
    val category: String,
    val pinned: Boolean,
    val archived: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
