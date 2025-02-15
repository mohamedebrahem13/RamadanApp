package com.example.ramadanapp.features.home.data.models.dto

import com.google.gson.annotations.SerializedName

data class VideoDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("thumbnail")
    val thumbnail: String? = null // Add this to match API response
)
