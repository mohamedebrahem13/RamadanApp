package com.example.ramadanapp.features.home.home_content.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.ramadanapp.common.data.repository.local.Converters

@Entity(tableName = "ramadan_response")
@TypeConverters(Converters::class) // Required for List<SectionEntity> and List<ItemEntity>
data class RamadanResponseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sections: List<SectionEntity>, // TypeConverter needed
    val items: List<ItemEntity> // TypeConverter needed
)