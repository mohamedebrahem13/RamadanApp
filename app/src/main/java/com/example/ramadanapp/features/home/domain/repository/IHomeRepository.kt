package com.example.ramadanapp.features.home.domain.repository

import com.example.ramadanapp.features.home.domain.models.Home

interface IHomeRepository {
    suspend fun getHomeData(): Home
}