package com.practicum.playlist_maker_android_magomedovaalina

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun PlaylistHost(navController: NavHostController) {

    //методы переходов. вся логика навигации живёт только здесь
    fun navigateToSearch() {
        navController.navigate(Screen.SEARCH.name)
    }

    fun navigateToSettings() {
        navController.navigate(Screen.SETTINGS.name)
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    NavHost(
        navController = navController,
        startDestination = Screen.MAIN.name
    ) {
        composable(Screen.MAIN.name) {
            MainScreen(
                onSearchClick = { navigateToSearch() },
                onSettingsClick = { navigateToSettings() }
            )
        }
        composable(Screen.SEARCH.name) {
            SearchScreen(onBackClick = { navigateBack() })
        }
        composable(Screen.SETTINGS.name) {
            SettingsScreen()
        }
    }
}