package com.example.ramadanapp.features.home.homescreen.data.models.dto

import com.google.gson.annotations.SerializedName

data class VideoItemDto(
    @SerializedName("subCategory")
    val subCategory: String?,

    @SerializedName("category")
    val category: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val url: String?
)