package com.secondbrain.ai.domain.usecase

import com.secondbrain.ai.domain.model.AIResponse
import com.secondbrain.ai.domain.model.Note
import com.secondbrain.ai.domain.repository.AIRepository

class AiUseCases(
    private val repository: AIRepository
) {
    suspend fun summarize(note: Note): Result<AIResponse> = repository.summarize(note)
    suspend fun rewrite(note: Note): Result<AIResponse> = repository.rewrite(note)
    suspend fun generateTags(note: Note): Result<List<String>> = repository.generateTags(note)
    suspend fun generateFlashcards(note: Note): Result<List<String>> = repository.generateFlashcards(note)
    suspend fun semanticSearch(query: String): Result<List<Note>> = repository.semanticSearch(query)
}
