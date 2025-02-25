package com.example.ramadanapp.features.home.home_content.data.repository

import com.example.ramadanapp.features.home.home_content.data.mapper.RamadanResponseMapper
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import com.example.ramadanapp.features.home.home_content.domain.repository.IHomeRepository
import com.example.ramadanapp.features.home.home_content.domain.repository.local.IHomeLocalDS
import com.example.ramadanapp.features.home.home_content.domain.repository.remote.IHomeRemoteDS
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteDataSource: IHomeRemoteDS,
    private val localDataSource: IHomeLocalDS
) : IHomeRepository {

    override suspend fun getHomeDataFromRemote(): RamadanResponse {
        val response = remoteDataSource.fetchHomeData()
        return RamadanResponseMapper.dtoToDomain(response)
    }

    override suspend fun getHomeDataFromLocal(): RamadanResponse {
        val response = localDataSource.getRamadanResponse()
        return RamadanResponseMapper.entityToDomain(response)
    }

    override suspend fun saveRamadanResponse(response: RamadanResponse) {
        val entity = RamadanResponseMapper.domainToEntity(response)
        localDataSource.saveRamadanResponse(entity)
    }
}