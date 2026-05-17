package com.secondbrain.ai.feature.search.presentation

import com.secondbrain.ai.domain.model.Note

data class SearchUiState(
    val query: String = "",
    val results: List<Note> = emptyList(),
    val isLoading: Boolean = false
)
