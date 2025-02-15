package com.example.ramadanapp.features.home.data.repository

import com.example.ramadanapp.features.home.data.mapper.HomeMapper
import com.example.ramadanapp.features.home.domain.models.Home
import com.example.ramadanapp.features.home.domain.repository.IHomeRepository
import com.example.ramadanapp.features.home.domain.repository.remote.IHomeRemoteDS
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteDataSource: IHomeRemoteDS
) : IHomeRepository {

    override suspend fun getHomeData(): Home {
        val response = remoteDataSource.fetchHomeData()
        return HomeMapper.dtoToDomain(response)
    }
}