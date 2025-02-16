package com.example.ramadanapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.ramadanapp.android.theme.RamadanAppTheme
import com.example.ramadanapp.features.home.ui.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RamadanAppTheme {
                val navController = rememberNavController()
                HomeScreen(
                    navController = navController
                )
            }
        }
    }
}
