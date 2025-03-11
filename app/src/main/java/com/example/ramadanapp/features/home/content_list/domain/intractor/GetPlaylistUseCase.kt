package com.example.ramadanapp.features.home.content_list.domain.intractor

import com.example.ramadanapp.common.domain.intractor.BaseUseCase
import com.example.ramadanapp.features.home.content_list.domain.models.VideoList
import com.example.ramadanapp.features.home.content_list.domain.repository.IContentListRepository
import javax.inject.Inject

class GetPlaylistUseCase @Inject constructor(
    private val repository: IContentListRepository
) : BaseUseCase<VideoList, String>() {
    override suspend fun execute(params: String?): VideoList {
        require(!params.isNullOrBlank()) { "Playlist ID must not be null or empty." }
        return repository.getPlaylist(params)
    }
}