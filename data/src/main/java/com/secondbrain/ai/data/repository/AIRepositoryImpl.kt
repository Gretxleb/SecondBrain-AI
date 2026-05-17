package com.secondbrain.ai.data.repository

import com.secondbrain.ai.data.local.dao.NoteDao
import com.secondbrain.ai.data.local.database.toDomain
import com.secondbrain.ai.data.remote.OpenAIChatRequest
import com.secondbrain.ai.data.remote.OpenAIMessage
import com.secondbrain.ai.data.remote.OpenAIService
import kotlinx.coroutines.flow.first
import com.secondbrain.ai.domain.model.AIResponse
import com.secondbrain.ai.domain.model.Note
import com.secondbrain.ai.domain.repository.AIRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AIRepositoryImpl @Inject constructor(
    private val openAIService: OpenAIService,
    private val noteDao: NoteDao
) : AIRepository {
    override suspend fun summarize(note: Note): Result<AIResponse> {
        return executePrompt("Summarize the following note:", note)
    }

    override suspend fun rewrite(note: Note): Result<AIResponse> {
        return executePrompt("Rewrite the note in a clearer concise tone:", note)
    }

    override suspend fun generateTags(note: Note): Result<List<String>> {
        return try {
            val prompt = "Generate concise tags for this note: ${note.title} ${note.content}"
            val response = openAIService.createChatCompletion(OpenAIChatRequest(messages = listOf(OpenAIMessage("user", prompt))))
            val content = response.choices.firstOrNull()?.message?.content.orEmpty()
            Result.success(content.split(',').map { it.trim() }.filter { it.isNotBlank() })
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun generateFlashcards(note: Note): Result<List<String>> {
        return try {
            val prompt = "Generate three flashcard questions and answers based on the following note: ${note.title} ${note.content}"
            val response = openAIService.createChatCompletion(OpenAIChatRequest(messages = listOf(OpenAIMessage("user", prompt))))
            val content = response.choices.firstOrNull()?.message?.content.orEmpty()
            Result.success(content.split('\n').map { it.trim() }.filter { it.isNotBlank() })
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun semanticSearch(query: String): Result<List<Note>> {
        return withContext(Dispatchers.IO) {
            try {
                val entities = noteDao.search(query).first()
                Result.success(entities.map { it.toDomain() })
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
    }

    private suspend fun executePrompt(prompt: String, note: Note): Result<AIResponse> {
        return try {
            val response = openAIService.createChatCompletion(OpenAIChatRequest(messages = listOf(OpenAIMessage("user", "$prompt ${note.title} ${note.content}"))))
            val content = response.choices.firstOrNull()?.message?.content.orEmpty()
            Result.success(AIResponse(id = response.id, summary = content, flashcards = listOf(), keywords = listOf()))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
