package com.secondbrain.ai.feature.voice.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secondbrain.ai.domain.model.VoiceMemo
import com.secondbrain.ai.domain.usecase.VoiceUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class VoiceViewModel @Inject constructor(
    private val voiceUseCases: VoiceUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(VoiceUiState())
    val uiState: StateFlow<VoiceUiState> = _uiState

    init {
        viewModelScope.launch {
            voiceUseCases.streamVoiceMemos().collect { memos ->
                _uiState.update { it.copy(voiceMemos = memos) }
            }
        }
    }

    fun onTranscriptChanged(transcript: String) {
        _uiState.update { it.copy(transcript = transcript) }
    }

    fun onSaveVoiceMemo() {
        viewModelScope.launch {
            val memo = VoiceMemo(
                id = UUID.randomUUID().toString(),
                noteId = UUID.randomUUID().toString(),
                transcript = _uiState.value.transcript,
                audioUrl = "local://${UUID.randomUUID()}",
                createdAt = System.currentTimeMillis()
            )
            voiceUseCases.saveVoiceMemo(memo)
            _uiState.update { it.copy(transcript = "") }
        }
    }
}
