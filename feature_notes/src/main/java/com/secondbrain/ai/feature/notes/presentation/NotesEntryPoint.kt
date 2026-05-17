package com.secondbrain.ai.feature.notes.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

@Composable
fun NotesEntryPoint(
    onOpenAssistant: () -> Unit,
    onOpenSearch: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenVoice: () -> Unit,
    onOpenReminders: () -> Unit,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val notes = uiState.notes.collectAsLazyPagingItems()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(text = "My Notes", style = MaterialTheme.typography.titleLarge)
        Button(onClick = onOpenAssistant, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Open AI Assistant")
        }
        Button(onClick = onOpenSearch, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Search Notes")
        }
        Button(onClick = onOpenVoice, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Voice Notes")
        }
        Button(onClick = onOpenReminders, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Reminders")
        }
        Button(onClick = onOpenSettings, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Settings")
        }
        LazyColumn(contentPadding = PaddingValues(vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(notes, key = { it.id }) { note ->
                if (note != null) {
                    NoteCard(note = note, onClick = {})
                }
            }
        }
    }
}

@Composable
fun NoteCard(note: com.secondbrain.ai.domain.model.Note, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, style = MaterialTheme.typography.titleLarge, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = note.content, style = MaterialTheme.typography.bodyLarge, maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(top = 8.dp))
        }
    }
}
