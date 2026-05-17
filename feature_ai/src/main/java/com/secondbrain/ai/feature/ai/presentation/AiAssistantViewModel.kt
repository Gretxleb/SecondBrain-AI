package com.secondbrain.ai.feature.ai.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secondbrain.ai.domain.usecase.AiUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AiAssistantViewModel @Inject constructor(
    private val aiUseCases: AiUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(AiAssistantUiState())
    val uiState: StateFlow<AiAssistantUiState> = _uiState

    fun onPromptChanged(prompt: String) {
        _uiState.update { it.copy(prompt = prompt) }
    }

    fun onSubmitPrompt() {
        viewModelScope.launch {
            val prompt = _uiState.value.prompt
            _uiState.update { it.copy(isLoading = true) }
            val result = aiUseCases.semanticSearch(prompt)
            _uiState.update { state ->
                state.copy(
                    isLoading = false,
                    result = result.getOrDefault(emptyList()).joinToString("\n\n") { "• ${it.title}" },
                    error = result.exceptionOrNull()?.localizedMessage
                )
            }
        }
    }
}
