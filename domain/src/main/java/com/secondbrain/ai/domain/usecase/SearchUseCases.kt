package com.secondbrain.ai.domain.usecase

import com.secondbrain.ai.domain.model.Note
import com.secondbrain.ai.domain.model.SearchQuery
import com.secondbrain.ai.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCases(
    private val repository: SearchRepository
) {
    fun searchNotes(query: SearchQuery): Flow<List<Note>> = repository.searchNotes(query)
    suspend fun semanticSearch(query: String): Result<List<Note>> = repository.semanticSearch(query)
}
