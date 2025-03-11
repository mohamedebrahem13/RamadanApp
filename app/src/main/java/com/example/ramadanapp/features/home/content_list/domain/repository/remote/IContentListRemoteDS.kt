package com.example.ramadanapp.features.home.content_list.domain.repository.remote

import com.example.ramadanapp.features.home.content_list.data.models.VideoItemDto

interface IContentListRemoteDS {
    suspend fun fetchPlaylist(playlistId: String): List<VideoItemDto>
}