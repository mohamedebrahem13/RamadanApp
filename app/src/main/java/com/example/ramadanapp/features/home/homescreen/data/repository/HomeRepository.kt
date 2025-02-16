package com.example.ramadanapp.features.home.homescreen.data.repository

import com.example.ramadanapp.features.home.homescreen.data.mapper.VideoMapper
import com.example.ramadanapp.features.home.homescreen.domain.models.Video
import com.example.ramadanapp.features.home.homescreen.domain.repository.IHomeRepository
import com.example.ramadanapp.features.home.homescreen.domain.repository.remote.IHomeRemoteDS
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteDataSource: IHomeRemoteDS
) : IHomeRepository {

    override suspend fun getHomeData(): List<Video> {
        val response = remoteDataSource.fetchHomeData()
        return VideoMapper.dtoListToDomainList(response)
    }
}