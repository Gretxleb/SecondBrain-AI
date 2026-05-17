package com.secondbrain.ai.domain.model

data class Reminder(
    val id: String,
    val noteId: String,
    val title: String,
    val description: String,
    val time: Long,
    val active: Boolean
)
