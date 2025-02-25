package com.example.ramadanapp.features.home.home_content.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.ramadanapp.common.data.repository.local.Converters


@Entity(tableName = "sections")
@TypeConverters(Converters::class) // Required for List<CategoryEntity>
data class SectionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val categories: List<CategoryEntity> // TypeConverter needed
)