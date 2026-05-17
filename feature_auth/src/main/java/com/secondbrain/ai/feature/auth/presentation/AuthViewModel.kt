package com.secondbrain.ai.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secondbrain.ai.domain.usecase.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun onEmailChanged(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun onSignIn() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = authUseCases.loginWithEmail(_uiState.value.email, _uiState.value.password)
            _uiState.update { state ->
                if (result.isSuccess) state.copy(isAuthenticated = true, isLoading = false)
                else state.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.localizedMessage)
            }
        }
    }

    fun onRegister() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = authUseCases.registerWithEmail(_uiState.value.email, _uiState.value.password)
            _uiState.update { state ->
                if (result.isSuccess) state.copy(isAuthenticated = true, isLoading = false)
                else state.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.localizedMessage)
            }
        }
    }
}
