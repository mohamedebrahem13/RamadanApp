package com.example.ramadanapp.features.home.home_content.domain.models

data class RamadanResponse(
    val sections: List<Section>,
    val totalVideoCount: Int,
    val playlistCount: Int
)
