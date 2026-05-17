package com.secondbrain.ai.feature.reminders.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secondbrain.ai.domain.model.Reminder
import com.secondbrain.ai.domain.usecase.RemindersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RemindersViewModel @Inject constructor(
    private val remindersUseCases: RemindersUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(RemindersUiState())
    val uiState: StateFlow<RemindersUiState> = _uiState

    init {
        viewModelScope.launch {
            remindersUseCases.streamReminders().collect { reminders ->
                _uiState.update { it.copy(reminders = reminders) }
            }
        }
    }

    fun onTitleChanged(title: String) {
        _uiState.update { it.copy(reminderTitle = title) }
    }

    fun onDescriptionChanged(description: String) {
        _uiState.update { it.copy(reminderDescription = description) }
    }

    fun onScheduleReminder() {
        viewModelScope.launch {
            remindersUseCases.scheduleReminder(Reminder(
                id = UUID.randomUUID().toString(),
                noteId = UUID.randomUUID().toString(),
                title = _uiState.value.reminderTitle,
                description = _uiState.value.reminderDescription,
                time = System.currentTimeMillis() + 60_000,
                active = true
            ))
            _uiState.update { it.copy(reminderTitle = "", reminderDescription = "") }
        }
    }
}
