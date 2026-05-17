package com.secondbrain.ai.domain.repository

import com.secondbrain.ai.domain.model.AIResponse
import com.secondbrain.ai.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface AIRepository {
    suspend fun summarize(note: Note): Result<AIResponse>
    suspend fun rewrite(note: Note): Result<AIResponse>
    suspend fun generateTags(note: Note): Result<List<String>>
    suspend fun generateFlashcards(note: Note): Result<List<String>>
    suspend fun semanticSearch(query: String): Result<List<Note>>
}
