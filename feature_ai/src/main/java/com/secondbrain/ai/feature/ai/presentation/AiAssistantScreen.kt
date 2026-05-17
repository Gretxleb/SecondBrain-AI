package com.secondbrain.ai.feature.ai.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AiAssistantScreen(onBack: () -> Unit, viewModel: AiAssistantViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(text = "AI Assistant", style = MaterialTheme.typography.titleLarge)
            OutlinedTextField(value = uiState.prompt, onValueChange = viewModel::onPromptChanged, label = { Text("Ask the assistant") }, modifier = Modifier.fillMaxWidth())
            Button(onClick = viewModel::onSubmitPrompt, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Run AI")
            }
            Text(text = uiState.result, style = MaterialTheme.typography.bodyLarge)
            Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Back")
            }
        }
    }
}
