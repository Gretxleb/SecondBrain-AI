package com.secondbrain.ai.feature.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(onBack: () -> Unit, viewModel: SettingsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Settings", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = uiState.language, onValueChange = viewModel::onLanguageChanged, label = { Text("Language") }, modifier = Modifier.fillMaxWidth())
        RowWithLabel(text = "Dark mode") {
            Switch(checked = uiState.darkModeEnabled, onCheckedChange = viewModel::onDarkModeToggled)
        }
        RowWithLabel(text = "Biometric unlock") {
            Switch(checked = uiState.biometricEnabled, onCheckedChange = viewModel::onBiometricToggled)
        }
        RowWithLabel(text = "Sync enabled") {
            Switch(checked = uiState.syncEnabled, onCheckedChange = viewModel::onSyncToggled)
        }
        Button(onClick = { viewModel.onSaveSettings(); onBack() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Save Settings")
        }
    }
}

@Composable
fun RowWithLabel(text: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
        content()
    }
}
