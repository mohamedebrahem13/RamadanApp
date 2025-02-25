package com.example.ramadanapp.features.home.home_content.domain.repository

import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse

interface IHomeRepository {
    suspend fun getHomeDataFromRemote(): RamadanResponse
    suspend fun getHomeDataFromLocal(): RamadanResponse
    suspend fun saveRamadanResponse(response: RamadanResponse)
}