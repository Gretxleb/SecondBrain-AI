package com.secondbrain.ai.domain.repository

import androidx.paging.PagingData
import com.secondbrain.ai.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun streamNotes(): Flow<PagingData<Note>>
    suspend fun getNoteById(id: String): Result<Note>
    suspend fun saveNote(note: Note): Result<Unit>
    suspend fun deleteNote(id: String): Result<Unit>
    suspend fun archiveNote(id: String, archived: Boolean): Result<Unit>
    suspend fun pinNote(id: String, pinned: Boolean): Result<Unit>
}
