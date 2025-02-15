package com.example.ramadanapp.features.home.data.models.dto

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("videos")
    val videos: List<VideoDto>? = null
)
