package com.example.ramadanapp.features.home.content_list.domain.models

data class VideoItem(
    val title: String,
    val url: String,
    val thumbnail: String,
    val videoId: String,
    val playlistId: String
)