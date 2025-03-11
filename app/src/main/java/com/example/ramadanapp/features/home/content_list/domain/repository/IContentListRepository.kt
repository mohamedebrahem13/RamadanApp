package com.example.ramadanapp.features.home.content_list.domain.repository

import com.example.ramadanapp.features.home.content_list.domain.models.VideoList

interface IContentListRepository {
    suspend fun getPlaylist(playlistId: String): VideoList

}