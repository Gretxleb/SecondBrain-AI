package com.secondbrain.ai.feature.notes.presentation

import androidx.paging.PagingData
import com.secondbrain.ai.domain.model.Note
import kotlinx.coroutines.flow.emptyFlow

data class NotesUiState(
    val notes: PagingData<Note> = PagingData.empty()
)
