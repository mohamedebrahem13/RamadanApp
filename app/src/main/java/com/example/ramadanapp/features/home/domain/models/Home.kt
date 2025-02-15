package com.example.ramadanapp.features.home.domain.models

data class Home(
    val featuredVideo: Video,
    val categories: List<Category>,
    val hadith: Hadith
)