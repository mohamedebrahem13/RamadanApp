package com.example.ramadanapp.features.home.home_content.data.models.dto

import com.google.gson.annotations.SerializedName

data class RamadanResponseDto(
    @SerializedName("sections")
    val sections: List<SectionDto>?,

    @SerializedName("items")
    val items: List<ItemDto>?
)
