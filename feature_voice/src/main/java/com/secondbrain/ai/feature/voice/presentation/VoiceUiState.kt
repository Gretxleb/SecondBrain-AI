package com.secondbrain.ai.feature.voice.presentation

import com.secondbrain.ai.domain.model.VoiceMemo

data class VoiceUiState(
    val transcript: String = "",
    val voiceMemos: List<VoiceMemo> = emptyList()
)
