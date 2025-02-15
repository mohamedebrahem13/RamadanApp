package com.example.ramadanapp.features.home.data.models.dto

import com.google.gson.annotations.SerializedName

data class HomeResponseDto(
    @SerializedName("featured_video")
    val featuredVideo: VideoDto? = null,

    @SerializedName("categories")
    val categories: List<CategoryDto>? = null,

    @SerializedName("hadith")
    val hadith: HadithDto? = null
)