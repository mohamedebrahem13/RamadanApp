

package com.example.ramadanapp.features.home.ui

import com.example.ramadanapp.R

sealed class Routes(val route: String, val title: String, val icon: Int) {
    data object Home : Routes("home", "الرئيسية", R.drawable.home_24dp)
    data object NewContent : Routes("new_content", "كل ماهو جديد", R.drawable.video_library_24)
    data object Favorites : Routes("favorites", "المفضلة", R.drawable.star_24dp)
    data object Downloads : Routes("downloads", "التنزيلات", R.drawable.arrow_circle_down_24dp)
}
