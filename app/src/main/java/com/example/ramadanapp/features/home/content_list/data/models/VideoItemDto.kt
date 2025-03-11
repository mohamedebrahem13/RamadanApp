package com.example.ramadanapp.features.home.content_list.data.models

import com.google.gson.annotations.SerializedName

data class VideoItemDto(
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("thumbnail") val thumbnail: String?,
    @SerializedName("video_id") val videoId: String?,
    @SerializedName("playlist_id") val playlistId: String?
)