package com.example.ramadanapp.common.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ramadanapp.features.home.home_content.data.models.entity.CategoryEntity
import com.example.ramadanapp.features.home.home_content.data.models.entity.ItemEntity
import com.example.ramadanapp.features.home.home_content.data.models.entity.RamadanResponseEntity
import com.example.ramadanapp.features.home.home_content.data.models.entity.SectionEntity
import com.example.ramadanapp.features.home.home_content.data.repository.local.RamadanResponseDao

@Database(
    entities = [CategoryEntity::class, SectionEntity::class, ItemEntity::class, RamadanResponseEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)  // ✅ Now it works because Converters is in common
abstract class AppDatabase : RoomDatabase() {
    abstract fun ramadanResponseDao(): RamadanResponseDao
}