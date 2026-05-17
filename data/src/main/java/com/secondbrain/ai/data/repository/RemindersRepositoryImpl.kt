package com.secondbrain.ai.data.repository

import com.secondbrain.ai.data.local.dao.ReminderDao
import com.secondbrain.ai.data.local.database.toDomain
import com.secondbrain.ai.data.local.database.toEntity
import com.secondbrain.ai.domain.model.Reminder
import com.secondbrain.ai.domain.repository.RemindersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemindersRepositoryImpl @Inject constructor(
    private val reminderDao: ReminderDao
) : RemindersRepository {
    override fun streamReminders(): Flow<List<Reminder>> {
        return reminderDao.streamReminders().map { it.map { reminder -> reminder.toDomain() } }
    }

    override suspend fun scheduleReminder(reminder: Reminder): Result<Unit> {
        return try {
            reminderDao.upsert(reminder.toEntity())
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun cancelReminder(id: String): Result<Unit> {
        return try {
            reminderDao.deleteById(id)
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
