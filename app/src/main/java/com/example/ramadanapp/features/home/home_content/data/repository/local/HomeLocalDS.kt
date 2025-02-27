package com.example.ramadanapp.features.home.home_content.data.repository.local

import com.example.ramadanapp.features.home.home_content.data.models.entity.RamadanResponseEntity
import com.example.ramadanapp.features.home.home_content.domain.repository.local.IHomeLocalDS
import javax.inject.Inject

class HomeLocalDS  @Inject constructor(private val ramadanResponseDao: RamadanResponseDao):IHomeLocalDS {
    override suspend fun saveRamadanResponse(response: RamadanResponseEntity) {
        ramadanResponseDao.insertRamadanResponse(response)
    }

    override suspend fun getRamadanResponse(): RamadanResponseEntity {
        return ramadanResponseDao.getRamadanResponse()
    }
}