package com.example.ramadanapp.features.home.domain.repository.remote

import com.example.ramadanapp.features.home.data.models.dto.HomeResponseDto

interface IHomeRemoteDS {
    suspend fun fetchHomeData(): HomeResponseDto
}