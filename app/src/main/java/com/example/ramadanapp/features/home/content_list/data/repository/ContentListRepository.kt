package com.example.ramadanapp.features.home.content_list.data.repository

import android.util.Log
import com.example.ramadanapp.features.home.content_list.data.mapper.ListMapper
import com.example.ramadanapp.features.home.content_list.domain.models.VideoList
import com.example.ramadanapp.features.home.content_list.domain.repository.IContentListRepository
import com.example.ramadanapp.features.home.content_list.domain.repository.remote.IContentListRemoteDS
import javax.inject.Inject

class ContentListRepository @Inject constructor(
    private val remoteDS: IContentListRemoteDS
) : IContentListRepository {

    override suspend fun getPlaylist(playlistId: String): VideoList {
        Log.d("ContentListRepository", "Fetched playlist with ID: $playlistId")
        val dto = remoteDS.fetchPlaylist(playlistId)
        Log.d("ContentListRepository", "DTO: $dto")
        return ListMapper.dtoToDomain(dto)
    }
}