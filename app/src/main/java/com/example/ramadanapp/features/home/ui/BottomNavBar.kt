package com.example.ramadanapp.features.home.ui

import android.util.Log
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
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ramadanapp.R

@Composable
fun BottomNavBar(navController: NavController) {
    val topLevelRoutes  = listOf(
        TopLevelRoute(destination = Destination.HomeGraph, title = "الرئيسية", icon = R.drawable.home_24dp),
        TopLevelRoute(destination = Destination.NewContentGraph, title = "كل ماهو جديد", icon = R.drawable.video_library_24),
        TopLevelRoute(destination = Destination.FavoritesGraph, title = "المفضلة", icon = R.drawable.star_24dp),
        TopLevelRoute(destination = Destination.DownloadsGraph, title = "التنزيلات", icon = R.drawable.arrow_circle_down_24dp),
    )
    NavigationBar(containerColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination

        topLevelRoutes.forEach { graphDestination ->
            val isSelected = currentRoute?.hierarchy?.any { it.hasRoute(graphDestination.destination::class) } == true

            NavigationBarItem(
                icon = {
                    Image(
                        painter = painterResource(graphDestination.icon),
                        contentDescription = graphDestination.title,
                        colorFilter = ColorFilter.tint(
                            if (isSelected) Color.Green else Color.Gray
                        )
                    )
                },
                label = {
                    Text(
                        text = graphDestination.title,
                        color = if (isSelected) Color.Green else Color.Black

                    )
                },
                selected =isSelected,
                onClick = {
                    Log.d("BottomNavBar", "Selected route: ${graphDestination.destination}")

                    navController.navigate(graphDestination.destination) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
data class TopLevelRoute(val destination: Destination, val icon: Int, val title: String)
