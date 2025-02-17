package com.example.ramadanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ramadanapp.android.theme.RamadanAppTheme
import com.example.ramadanapp.features.home.ui.BottomNavBar
import com.example.ramadanapp.features.home.ui.Destination
import com.example.ramadanapp.features.home.ui.downloadsGraph
import com.example.ramadanapp.features.home.ui.favoritesGraph
import com.example.ramadanapp.features.home.ui.homeGraph
import com.example.ramadanapp.features.home.ui.newContentGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RamadanAppTheme {
                HomeNavHost()
            }
        }
    }
}
@Composable
fun HomeNavHost() {
    val navController = rememberNavController()

    // Use Scaffold to add bottomBar and manage insets
    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        contentWindowInsets = WindowInsets(0) // Set content insets to avoid overlap
    ) { paddingValues ->
        // Wrap the NavHost with proper padding
        NavHost(
            navController = navController,
            startDestination = Destination.HomeGraph,
            modifier = Modifier.padding(paddingValues) // Apply padding from Scaffold
        ) {
            homeGraph(navController)
            newContentGraph()
            favoritesGraph()
            downloadsGraph()
        }
    }
}