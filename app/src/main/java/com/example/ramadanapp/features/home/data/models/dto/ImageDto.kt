package com.example.ramadanapp.features.home.data.models.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("image")
    val imageUrl: String? = null
)