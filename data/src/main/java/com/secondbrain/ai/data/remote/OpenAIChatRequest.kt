package com.secondbrain.ai.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class OpenAIMessage(
    val role: String,
    val content: String
)

@Serializable
data class OpenAIChatRequest(
    val model: String = "gpt-4o-mini",
    val messages: List<OpenAIMessage>,
    val temperature: Double = 0.7,
    val max_tokens: Int = 800
)
