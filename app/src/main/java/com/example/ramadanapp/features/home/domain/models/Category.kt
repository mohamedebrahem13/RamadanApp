package com.example.ramadanapp.features.home.domain.models


data class Category(
    val id: Int,
    val name: String,
    val videos: List<Video>?
)
