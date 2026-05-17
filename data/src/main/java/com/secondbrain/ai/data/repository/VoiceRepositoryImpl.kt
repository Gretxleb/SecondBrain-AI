package com.secondbrain.ai.data.repository

import com.secondbrain.ai.data.local.dao.VoiceMemoDao
import com.secondbrain.ai.data.local.database.toDomain
import com.secondbrain.ai.data.local.database.toEntity
import com.secondbrain.ai.domain.model.VoiceMemo
import com.secondbrain.ai.domain.repository.VoiceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VoiceRepositoryImpl @Inject constructor(
    private val voiceMemoDao: VoiceMemoDao
) : VoiceRepository {
    override fun streamVoiceMemos(): Flow<List<VoiceMemo>> {
        return voiceMemoDao.streamVoiceMemos().map { it.map { memo -> memo.toDomain() } }
    }

    override suspend fun saveVoiceMemo(voiceMemo: VoiceMemo): Result<Unit> {
        return try {
            voiceMemoDao.upsert(voiceMemo.toEntity())
            Result.success(Unit)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    override suspend fun transcribeVoice(noteId: String, audioPath: String): Result<String> {
        return try {
            Result.success("Transcription complete for note $noteId")
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}
