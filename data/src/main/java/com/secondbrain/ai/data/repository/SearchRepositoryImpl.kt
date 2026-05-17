package com.secondbrain.ai.data.repository

import com.secondbrain.ai.data.local.dao.NoteDao
import com.secondbrain.ai.data.local.database.toDomain
import com.secondbrain.ai.domain.model.Note
import com.secondbrain.ai.domain.model.SearchQuery
import com.secondbrain.ai.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : SearchRepository {
    override fun searchNotes(query: SearchQuery): Flow<List<Note>> {
        return noteDao.search(query.query).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun semanticSearch(query: String): Result<List<Note>> {
        return try {
            val results = noteDao.search(query).map { list -> list.map { it.toDomain() } }.first()
            Result.success(results)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
