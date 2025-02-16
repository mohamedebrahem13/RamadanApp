package com.example.ramadanapp.features.home.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        contentWindowInsets = WindowInsets(0)
    ) { paddingValues ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}

