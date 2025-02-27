package com.example.ramadanapp.features.home.home_content.data.models.dto

import com.google.gson.annotations.SerializedName

data class SectionDto(
    @SerializedName("title")
    val title: String?,

    @SerializedName("categories")
    val categories: List<CategoryDto>?
)
