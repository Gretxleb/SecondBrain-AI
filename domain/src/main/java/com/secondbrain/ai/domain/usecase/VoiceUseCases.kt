package com.secondbrain.ai.domain.usecase

import com.secondbrain.ai.domain.model.VoiceMemo
import com.secondbrain.ai.domain.repository.VoiceRepository
import kotlinx.coroutines.flow.Flow

class VoiceUseCases(
    private val repository: VoiceRepository
) {
    fun streamVoiceMemos(): Flow<List<VoiceMemo>> = repository.streamVoiceMemos()
    suspend fun saveVoiceMemo(voiceMemo: VoiceMemo): Result<Unit> = repository.saveVoiceMemo(voiceMemo)
    suspend fun transcribeVoice(noteId: String, audioPath: String): Result<String> = repository.transcribeVoice(noteId, audioPath)
}
