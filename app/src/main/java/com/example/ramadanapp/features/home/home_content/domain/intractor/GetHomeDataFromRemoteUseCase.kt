package com.example.ramadanapp.features.home.home_content.domain.intractor

import com.example.ramadanapp.common.domain.intractor.BaseUseCase
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import com.example.ramadanapp.features.home.home_content.domain.repository.IHomeRepository

class GetHomeDataFromRemoteUseCase(
    private val homeRepository: IHomeRepository
): BaseUseCase<RamadanResponse, Unit>(){

    override suspend fun execute(params: Unit?): RamadanResponse {
        val response  = homeRepository.getHomeDataFromRemote()
        homeRepository.saveRamadanResponse(response)
        return response
    }

}