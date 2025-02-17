package com.example.ramadanapp.features.home.ui
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.ramadanapp.features.home.homescreen.ui.ContentListScreen
import com.example.ramadanapp.features.home.homescreen.ui.HomeScreenContent
import com.example.ramadanapp.features.home.newcontent.ui.NewContentScreen
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
    data class ContentList(val category: String, val videoUrl: String) : Destination()

    @Serializable
    data object HomeGraph : Destination()

    @Serializable
    data object NewContentGraph : Destination()

    @Serializable
    data object FavoritesGraph : Destination()

    @Serializable
    data object DownloadsGraph : Destination()
}

// Home Graph
fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation<Destination.HomeGraph>(startDestination = Destination.Home) {
        composable<Destination.Home> {
            HomeScreenContent(
                onVideoClick = { video ->
                    // Navigate to ContentList screen
                    navController.navigate(Destination.ContentList(video.category, video.url))

                }
            )
        }
        composable<Destination.ContentList> { backStackEntry ->
            val args = backStackEntry.toRoute<Destination.ContentList>()
            ContentListScreen(args.category, args.videoUrl)
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
        }
    }
}

// Downloads Graph
fun NavGraphBuilder.downloadsGraph() {
    navigation<Destination.DownloadsGraph>(startDestination = Destination.Downloads) {
        composable<Destination.Downloads> {
            // Downloads Screen UI
        }
    }
}