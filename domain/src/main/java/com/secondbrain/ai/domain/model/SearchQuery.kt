package com.secondbrain.ai.domain.model

data class SearchQuery(
    val query: String,
    val filters: Map<String, String>
)
