package com.example.ramadanapp.features.home.home_content.data.models.dto

import com.google.gson.annotations.SerializedName

data class RamadanResponseDto(
    @SerializedName("sections")
    val sections: List<SectionDto>?,
    @SerializedName("total_video_count") val totalVideoCount: Int?,
    @SerializedName("playlist_count") val playlistCount: Int?
)
