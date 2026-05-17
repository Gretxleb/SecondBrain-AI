package com.secondbrain.ai.navigation

sealed class NavigationDestination(val route: String) {
    object Splash : NavigationDestination("splash")
    object Auth : NavigationDestination("auth")
    object Notes : NavigationDestination("notes")
    object Assistant : NavigationDestination("assistant")
    object Search : NavigationDestination("search")
    object Settings : NavigationDestination("settings")
    object Voice : NavigationDestination("voice")
    object Reminders : NavigationDestination("reminders")
}
