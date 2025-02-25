package com.example.ramadanapp.features.home.home_content.data.repository.remote

import com.example.ramadanapp.common.domain.repository.remote.INetworkProvider
import com.example.ramadanapp.features.home.home_content.data.models.dto.RamadanResponseDto
import com.example.ramadanapp.features.home.home_content.domain.repository.remote.IHomeRemoteDS
import javax.inject.Inject

class HomeRemoteDS @Inject constructor(
    private val networkProvider: INetworkProvider
) : IHomeRemoteDS {

    override suspend fun fetchHomeData(): RamadanResponseDto {
        return networkProvider.get(
            responseWrappedModel = RamadanResponseDto::class.java,
            pathUrl = END_POINT
        )
    }

    companion object {
        private const val END_POINT = "MahmoudMabrok/MyDataCenter/main/ramadan.json"
    }
}