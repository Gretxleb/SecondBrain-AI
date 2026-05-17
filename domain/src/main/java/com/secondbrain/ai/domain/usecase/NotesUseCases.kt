package com.secondbrain.ai.domain.usecase

import androidx.paging.PagingData
import com.secondbrain.ai.domain.model.Note
import com.secondbrain.ai.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesUseCases(
    private val repository: NotesRepository
) {
    fun streamNotes(): Flow<PagingData<Note>> = repository.streamNotes()
    suspend fun getNoteById(id: String): Result<Note> = repository.getNoteById(id)
    suspend fun saveNote(note: Note): Result<Unit> = repository.saveNote(note)
    suspend fun deleteNote(id: String): Result<Unit> = repository.deleteNote(id)
    suspend fun archiveNote(id: String, archived: Boolean): Result<Unit> = repository.archiveNote(id, archived)
    suspend fun pinNote(id: String, pinned: Boolean): Result<Unit> = repository.pinNote(id, pinned)
}
