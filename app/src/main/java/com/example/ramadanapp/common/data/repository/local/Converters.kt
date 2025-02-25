package com.example.ramadanapp.common.data.repository.local

import androidx.room.TypeConverter
import com.example.ramadanapp.common.extentions.getModelFromJSON
import com.example.ramadanapp.common.extentions.toJson
import com.example.ramadanapp.features.home.home_content.data.models.entity.CategoryEntity
import com.example.ramadanapp.features.home.home_content.data.models.entity.ItemEntity
import com.example.ramadanapp.features.home.home_content.data.models.entity.SectionEntity
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromCategoryList(value: List<CategoryEntity>): String {
        return value.toJson()
    }

    @TypeConverter
    fun toCategoryList(value: String): List<CategoryEntity> {
        val type = object : TypeToken<List<CategoryEntity>>() {}.type
        return value.getModelFromJSON(type)
    }

    @TypeConverter
    fun fromSectionList(value: List<SectionEntity>): String {
        return value.toJson()
    }

    @TypeConverter
    fun toSectionList(value: String): List<SectionEntity> {
        val type = object : TypeToken<List<SectionEntity>>() {}.type
        return value.getModelFromJSON(type) ?: emptyList()
    }

    @TypeConverter
    fun fromItemList(value: List<ItemEntity>): String {
        return value.toJson()
    }

    @TypeConverter
    fun toItemList(value: String): List<ItemEntity> {
        val type = object : TypeToken<List<ItemEntity>>() {}.type
        return value.getModelFromJSON(type) ?: emptyList()
    }
}