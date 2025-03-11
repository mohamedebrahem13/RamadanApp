package com.example.ramadanapp.features.home.content_list.data.repository.remote

import com.example.ramadanapp.common.domain.repository.remote.INetworkProvider
import com.example.ramadanapp.features.home.content_list.data.models.VideoItemDto
import com.example.ramadanapp.features.home.content_list.domain.repository.remote.IContentListRemoteDS
import javax.inject.Inject

class ContentListRemoteDS  @Inject constructor(
    private val networkProvider: INetworkProvider
) : IContentListRemoteDS {

    override suspend fun fetchPlaylist(playlistId: String): List<VideoItemDto> {
        val response: Array<VideoItemDto> = networkProvider.get(
            responseWrappedModel = Array<VideoItemDto>::class.java,
            pathUrl = "playlists/$playlistId.json"
        )
        return response.toList() // Convert Array to List
    }

}