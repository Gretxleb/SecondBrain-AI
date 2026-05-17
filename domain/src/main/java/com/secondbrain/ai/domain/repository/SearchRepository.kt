package com.secondbrain.ai.domain.repository

import com.secondbrain.ai.domain.model.Note
import com.secondbrain.ai.domain.model.SearchQuery
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchNotes(query: SearchQuery): Flow<List<Note>>
    suspend fun semanticSearch(query: String): Result<List<Note>>
}
