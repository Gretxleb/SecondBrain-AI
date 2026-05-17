package com.secondbrain.ai.feature.reminders.presentation

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RemindersScreen(onBack: () -> Unit, viewModel: RemindersViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Reminders", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = uiState.reminderTitle, onValueChange = viewModel::onTitleChanged, label = { Text("Title") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = uiState.reminderDescription, onValueChange = viewModel::onDescriptionChanged, label = { Text("Description") }, modifier = Modifier.fillMaxWidth())
        Button(onClick = viewModel::onScheduleReminder, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Schedule Reminder")
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxSize()) {
            items(uiState.reminders, key = { it.id }) { reminder ->
                Card(modifier = Modifier.fillMaxWidth().clickable {}) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = reminder.title, style = MaterialTheme.typography.titleLarge)
                        Text(text = reminder.description, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Back")
        }
    }
}
