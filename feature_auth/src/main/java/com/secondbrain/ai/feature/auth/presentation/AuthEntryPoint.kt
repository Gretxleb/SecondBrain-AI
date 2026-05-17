package com.secondbrain.ai.feature.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AuthEntryPoint(onAuthenticated: () -> Unit, viewModel: AuthViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState
    LaunchedEffect(uiState) {
        if (uiState.isAuthenticated) {
            onAuthenticated()
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome Back", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(value = uiState.email, onValueChange = viewModel::onEmailChanged, label = { Text("Email") }, modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 8.dp))
        OutlinedTextField(value = uiState.password, onValueChange = viewModel::onPasswordChanged, label = { Text("Password") }, modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp))
        Button(onClick = viewModel::onSignIn, modifier = Modifier.padding(bottom = 8.dp)) {
            Text(text = "Sign In")
        }
        TextButton(onClick = viewModel::onRegister) {
            Text(text = "Create account")
        }
    }
}
