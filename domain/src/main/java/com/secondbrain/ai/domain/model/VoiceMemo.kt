package com.secondbrain.ai.domain.model

data class VoiceMemo(
    val id: String,
    val noteId: String,
    val transcript: String,
    val audioUrl: String,
    val createdAt: Long
)
