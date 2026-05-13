package com.example.handz6.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

// navigation/AppNavGraph.kt
@Composable
fun AppNavegacion(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {

        }
        composable(Screen.Profile.route) {

        }
       /* // Con argumento:
        composable(
            route = Screen.DetailWithId.route,
            arguments = listOf(navArgument("itemId") { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId")
            DetailScreen(itemId = itemId)
        }*/
    }
}