package com.example.ramadanapp.features.home.home_content.data.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ramadanapp.features.home.home_content.data.models.entity.RamadanResponseEntity

@Dao
interface RamadanResponseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRamadanResponse(response: RamadanResponseEntity)

    @Query("SELECT * FROM ramadan_response LIMIT 1")
    suspend fun getRamadanResponse(): RamadanResponseEntity
}