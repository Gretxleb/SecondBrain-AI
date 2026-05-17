package com.secondbrain.ai.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.secondbrain.ai.data.local.dao.NoteDao
import com.secondbrain.ai.data.local.database.toDomain
import com.secondbrain.ai.data.local.database.toEntity
import com.secondbrain.ai.domain.model.Note
import com.secondbrain.ai.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotesRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NotesRepository {
    override fun streamNotes(): Flow<PagingData<Note>> {
        return Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            noteDao.streamAll()
        }.flow.map { pagingData -> pagingData.map { it.toDomain() } }
    }

    override suspend fun getNoteById(id: String): Result<Note> {
        return try {
            noteDao.findById(id)?.let { Result.success(it.toDomain()) }
                ?: Result.failure(Exception("Note not found"))
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun saveNote(note: Note): Result<Unit> {
        return try {
            noteDao.upsert(note.toEntity())
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun deleteNote(id: String): Result<Unit> {
        return try {
            noteDao.findById(id)?.let { noteDao.delete(it) }
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun archiveNote(id: String, archived: Boolean): Result<Unit> {
        return try {
            noteDao.updateArchived(id, archived)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun pinNote(id: String, pinned: Boolean): Result<Unit> {
        return try {
            noteDao.updatePinned(id, pinned)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
