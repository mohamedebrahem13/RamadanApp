package com.example.ramadanapp.features.home.homescreen.domain.repository

import com.example.ramadanapp.features.home.homescreen.domain.models.Video

interface IHomeRepository {
    suspend fun getHomeData(): List<Video>
}