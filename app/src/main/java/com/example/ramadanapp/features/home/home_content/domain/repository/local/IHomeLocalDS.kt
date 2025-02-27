package com.example.ramadanapp.features.home.home_content.domain.repository.local

import com.example.ramadanapp.features.home.home_content.data.models.entity.RamadanResponseEntity

interface IHomeLocalDS {
    suspend fun saveRamadanResponse(response: RamadanResponseEntity)
    suspend fun getRamadanResponse(): RamadanResponseEntity
}