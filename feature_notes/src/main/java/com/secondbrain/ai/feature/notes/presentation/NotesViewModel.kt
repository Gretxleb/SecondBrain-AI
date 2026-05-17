package com.secondbrain.ai.feature.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.secondbrain.ai.domain.model.Note
import kotlinx.coroutines.flow.collectLatest
import com.secondbrain.ai.domain.usecase.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState: StateFlow<NotesUiState> = _uiState

    init {
        viewModelScope.launch {
            notesUseCases.streamNotes().cachedIn(viewModelScope).collectLatest { pagingData ->
                _uiState.value = _uiState.value.copy(notes = pagingData)
            }
        }
    }
}
