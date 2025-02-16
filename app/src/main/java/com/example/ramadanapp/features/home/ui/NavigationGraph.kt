package com.example.ramadanapp.features.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ramadanapp.features.home.downloads.ui.DownloadsScreen
import com.example.ramadanapp.features.home.favorites.ui.FavoritesScreen
import com.example.ramadanapp.features.home.homescreen.ui.HomeScreenContent
import com.example.ramadanapp.features.home.newcontent.ui.NewContentScreen

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current // Get LifecycleOwner

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
        modifier = modifier
    ) {
        composable(Routes.Home.route) {
            HomeScreenContent(lifecycleOwner = lifecycleOwner,onVideoClick = {
            }) // Pass it here
        }
        composable(Routes.NewContent.route) { NewContentScreen() }
        composable(Routes.Favorites.route) { FavoritesScreen() }
        composable(Routes.Downloads.route) { DownloadsScreen() }
    }
}
