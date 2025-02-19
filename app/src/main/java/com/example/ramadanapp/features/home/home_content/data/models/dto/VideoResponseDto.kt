package com.example.ramadanapp.features.home.home_content.data.models.dto

import com.google.gson.annotations.SerializedName

data class VideoResponseDto(
    @SerializedName("data")
    val data: List<VideoItemDto>?
)