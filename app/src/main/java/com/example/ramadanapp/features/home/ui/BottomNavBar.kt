package com.example.ramadanapp.features.home.ui

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        Routes.Home,
        Routes.NewContent,
        Routes.Favorites,
        Routes.Downloads
    )

    NavigationBar(containerColor = Color.White) { // Set white background
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(screen.icon),
                        contentDescription = screen.title,
                        colorFilter = ColorFilter.tint(
                            if (currentRoute == screen.route) Color.Green else Color.Gray
                        ) // Green for selected, Gray for others
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        color = if (currentRoute == screen.route) Color.Green else Color.Black
                    )
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent // Removes indicator
                )
            )
        }
    }
}