package com.secondbrain.ai.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AIResponse(
    val id: String,
    val summary: String,
    val flashcards: List<String>,
    val keywords: List<String>
)
