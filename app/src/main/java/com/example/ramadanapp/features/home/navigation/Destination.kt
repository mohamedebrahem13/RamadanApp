package com.example.ramadanapp.features.home.navigation
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.ramadanapp.features.home.downloads.ui.DownloadsScreen
import com.example.ramadanapp.features.home.favorites.ui.FavoritesScreen
import com.example.ramadanapp.features.home.home_content.ui.content_list.ContentListScreen
import com.example.ramadanapp.features.home.home_content.ui.home_content.HomeScreenContent
import com.example.ramadanapp.features.home.newcontent.ui.NewContentScreen
import com.example.ramadanapp.features.home.settings.ui.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {

    @Serializable
    data object Home : Destination()

    @Serializable
    data object NewContent : Destination()

    @Serializable
    data object Favorites : Destination()

    @Serializable
    data object Downloads : Destination()
    @Serializable
    data object Settings : Destination()
    @Serializable
    data class ContentList(val categoryTitle: String) : Destination()

    @Serializable
    data object HomeGraph : Destination()

    @Serializable
    data object NewContentGraph : Destination()

    @Serializable
    data object FavoritesGraph : Destination()

    @Serializable
    data object DownloadsGraph : Destination()

    @Serializable
    data object SettingsGraph : Destination()
}

// Home Graph
fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation<Destination.HomeGraph>(startDestination = Destination.Home) {
        composable<Destination.Home> {
            HomeScreenContent(
                onCategoryClick = { categoryTitle ->
                    // Navigate to ContentList screen
                    navController.navigate(Destination.ContentList(categoryTitle))

                }
            )
        }
        composable<Destination.ContentList> { backStackEntry ->
            val args = backStackEntry.toRoute<Destination.ContentList>()
            ContentListScreen(args.categoryTitle){
                navController.popBackStack()
            }


        }
    }
}

// New Content Graph
fun NavGraphBuilder.newContentGraph() {
    navigation<Destination.NewContentGraph>(startDestination = Destination.NewContent) {
        composable<Destination.NewContent> {
            NewContentScreen(

            )
        }
    }
}

// Favorites Graph
fun NavGraphBuilder.favoritesGraph() {
    navigation<Destination.FavoritesGraph>(startDestination = Destination.Favorites) {
        composable<Destination.Favorites> {
            // Favorites Screen UI
            FavoritesScreen()
        }
    }
}

// Downloads Graph
fun NavGraphBuilder.downloadsGraph() {
    navigation<Destination.DownloadsGraph>(startDestination = Destination.Downloads) {
        composable<Destination.Downloads> {
            // Downloads Screen UI
            DownloadsScreen()
        }
    }
}
// Settings Graph
fun NavGraphBuilder.settingsGraph() {
    navigation<Destination.SettingsGraph>(startDestination = Destination.Settings) {
        composable<Destination.Settings> {
            // Settings Screen UI
            SettingsScreen()

        }
    }
}