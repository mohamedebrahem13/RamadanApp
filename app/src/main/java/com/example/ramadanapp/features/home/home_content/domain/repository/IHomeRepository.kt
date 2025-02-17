package com.example.ramadanapp.features.home.home_content.domain.repository

import com.example.ramadanapp.features.home.home_content.domain.models.Video

interface IHomeRepository {
    suspend fun getHomeData(): List<Video>
}