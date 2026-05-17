package com.secondbrain.ai.domain.model

import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class Note(
    val id: String,
    val title: String,
    val content: String,
    val tags: List<String>,
    val category: String,
    val pinned: Boolean,
    val archived: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
