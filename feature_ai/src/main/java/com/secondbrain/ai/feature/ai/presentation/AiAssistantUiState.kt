package com.secondbrain.ai.feature.ai.presentation

data class AiAssistantUiState(
    val prompt: String = "",
    val result: String = "",
    val error: String? = null,
    val isLoading: Boolean = false
)
