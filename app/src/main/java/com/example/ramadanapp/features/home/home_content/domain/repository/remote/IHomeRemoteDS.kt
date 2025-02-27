package com.example.ramadanapp.features.home.home_content.domain.repository.remote

import com.example.ramadanapp.features.home.home_content.data.models.dto.RamadanResponseDto

interface IHomeRemoteDS {
    suspend fun fetchHomeData(): RamadanResponseDto
}