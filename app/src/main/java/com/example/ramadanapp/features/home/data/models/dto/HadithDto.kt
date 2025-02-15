package com.example.ramadanapp.features.home.data.models.dto

import com.google.gson.annotations.SerializedName

data class HadithDto(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("text")
    val text: String? = null,

    @SerializedName("reference")
    val reference: String? = null
)