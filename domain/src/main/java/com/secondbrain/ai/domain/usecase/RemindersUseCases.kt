package com.secondbrain.ai.domain.usecase

import com.secondbrain.ai.domain.model.Reminder
import com.secondbrain.ai.domain.repository.RemindersRepository
import kotlinx.coroutines.flow.Flow

class RemindersUseCases(
    private val repository: RemindersRepository
) {
    fun streamReminders(): Flow<List<Reminder>> = repository.streamReminders()
    suspend fun scheduleReminder(reminder: Reminder): Result<Unit> = repository.scheduleReminder(reminder)
    suspend fun cancelReminder(id: String): Result<Unit> = repository.cancelReminder(id)
}
