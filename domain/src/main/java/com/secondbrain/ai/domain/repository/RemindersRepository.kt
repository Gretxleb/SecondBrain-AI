package com.secondbrain.ai.domain.repository

import com.secondbrain.ai.domain.model.Reminder
import kotlinx.coroutines.flow.Flow

interface RemindersRepository {
    fun streamReminders(): Flow<List<Reminder>>
    suspend fun scheduleReminder(reminder: Reminder): Result<Unit>
    suspend fun cancelReminder(id: String): Result<Unit>
}
