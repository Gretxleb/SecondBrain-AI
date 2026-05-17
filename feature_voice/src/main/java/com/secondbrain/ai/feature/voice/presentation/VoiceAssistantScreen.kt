package com.secondbrain.ai.feature.voice.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun VoiceAssistantScreen(onBack: () -> Unit, viewModel: VoiceViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Voice Notes", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = uiState.transcript, onValueChange = viewModel::onTranscriptChanged, label = { Text("Transcript") }, modifier = Modifier.fillMaxWidth())
        Button(onClick = viewModel::onSaveVoiceMemo, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save Voice Memo")
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxSize()) {
            items(uiState.voiceMemos, key = { it.id }) { memo ->
                Card(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = memo.audioUrl, style = MaterialTheme.typography.bodyLarge)
                        Text(text = memo.transcript, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Back")
        }
    }
}
