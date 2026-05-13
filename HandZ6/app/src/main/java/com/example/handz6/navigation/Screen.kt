package com.example.handz6.navigation

// navigation/Screen.kt
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Stats: Screen("stats")
    object Profile: Screen("profile")
    object MatchTracker: Screen("match")

    // Con argumentos:
    object DetailWithId : Screen("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }
}