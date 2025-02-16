package com.example.ramadanapp.features.home.homescreen.domain.repository.remote

import com.example.ramadanapp.features.home.homescreen.data.models.dto.VideoResponseDto

interface IHomeRemoteDS {
    suspend fun fetchHomeData(): VideoResponseDto
}