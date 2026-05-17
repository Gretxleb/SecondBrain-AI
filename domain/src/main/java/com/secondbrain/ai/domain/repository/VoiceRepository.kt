package com.secondbrain.ai.domain.repository

import com.secondbrain.ai.domain.model.VoiceMemo
import kotlinx.coroutines.flow.Flow

interface VoiceRepository {
    fun streamVoiceMemos(): Flow<List<VoiceMemo>>
    suspend fun saveVoiceMemo(voiceMemo: VoiceMemo): Result<Unit>
    suspend fun transcribeVoice(noteId: String, audioPath: String): Result<String>
}
