package com.example.ramadanapp.features.home.home_content.data.repository

import com.example.ramadanapp.features.home.home_content.data.mapper.VideoMapper
import com.example.ramadanapp.features.home.home_content.domain.models.Video
import com.example.ramadanapp.features.home.home_content.domain.repository.IHomeRepository
import com.example.ramadanapp.features.home.home_content.domain.repository.remote.IHomeRemoteDS
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteDataSource: IHomeRemoteDS
) : IHomeRepository {

    override suspend fun getHomeData(): List<Video> {
        val response = remoteDataSource.fetchHomeData()
        return VideoMapper.dtoListToDomainList(response)
    }
}