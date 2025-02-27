package com.example.ramadanapp.features.home.home_content.data.models.dto

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val url: String?
)