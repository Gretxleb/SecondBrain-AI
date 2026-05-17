package com.secondbrain.ai.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.secondbrain.ai.feature.auth.presentation.AuthEntryPoint
import com.secondbrain.ai.feature.notes.presentation.NotesEntryPoint
import com.secondbrain.ai.feature.ai.presentation.AiAssistantScreen
import com.secondbrain.ai.feature.search.presentation.SearchScreen
import com.secondbrain.ai.feature.settings.presentation.SettingsScreen
import com.secondbrain.ai.feature.voice.presentation.VoiceAssistantScreen
import com.secondbrain.ai.feature.reminders.presentation.RemindersScreen
import com.secondbrain.ai.feature.auth.presentation.SplashScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationDestination.Splash.route) {
        composable(NavigationDestination.Splash.route) {
            SplashScreen(onContinue = { navController.navigate(NavigationDestination.Auth.route) {
                popUpTo(NavigationDestination.Splash.route) { inclusive = true }
            } })
        }
        composable(NavigationDestination.Auth.route) {
            AuthEntryPoint(onAuthenticated = { navController.navigate(NavigationDestination.Notes.route) {
                popUpTo(NavigationDestination.Auth.route) { inclusive = true }
            } })
        }
        composable(NavigationDestination.Notes.route) {
            NotesEntryPoint(
                onOpenAssistant = { navController.navigate(NavigationDestination.Assistant.route) },
                onOpenSearch = { navController.navigate(NavigationDestination.Search.route) },
                onOpenSettings = { navController.navigate(NavigationDestination.Settings.route) },
                onOpenVoice = { navController.navigate(NavigationDestination.Voice.route) },
                onOpenReminders = { navController.navigate(NavigationDestination.Reminders.route) }
            )
        }
        composable(NavigationDestination.Assistant.route) {
            AiAssistantScreen(onBack = { navController.popBackStack() })
        }
        composable(NavigationDestination.Search.route) {
            SearchScreen(onBack = { navController.popBackStack() })
        }
        composable(NavigationDestination.Settings.route) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
        composable(NavigationDestination.Voice.route) {
            VoiceAssistantScreen(onBack = { navController.popBackStack() })
        }
        composable(NavigationDestination.Reminders.route) {
            RemindersScreen(onBack = { navController.popBackStack() })
        }
    }
}
