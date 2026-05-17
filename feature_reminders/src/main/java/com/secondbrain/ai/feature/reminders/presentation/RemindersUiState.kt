package com.secondbrain.ai.feature.reminders.presentation

import com.secondbrain.ai.domain.model.Reminder

data class RemindersUiState(
    val reminderTitle: String = "",
    val reminderDescription: String = "",
    val reminders: List<Reminder> = emptyList()
)
