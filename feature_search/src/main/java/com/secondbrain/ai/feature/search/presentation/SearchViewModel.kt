package com.secondbrain.ai.feature.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.secondbrain.ai.domain.model.SearchQuery
import com.secondbrain.ai.domain.usecase.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    fun onQueryChanged(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun onSearch() {
        viewModelScope.launch {
            val query = _uiState.value.query
            _uiState.update { it.copy(isLoading = true) }
            searchUseCases.searchNotes(SearchQuery(query, emptyMap())).collect { results ->
                _uiState.update { it.copy(results = results, isLoading = false) }
            }
        }
    }
}
