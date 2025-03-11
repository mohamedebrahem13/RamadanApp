package com.example.ramadanapp.common.data.repository.local

import androidx.room.TypeConverter
import com.example.ramadanapp.common.extentions.getModelFromJSON
import com.example.ramadanapp.common.extentions.toJson
import com.example.ramadanapp.features.home.home_content.data.models.entity.SectionEntity
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromSectionList(value: List<SectionEntity>): String {
        return value.toJson()
    }

    @TypeConverter
    fun toSectionList(value: String): List<SectionEntity> {
        val type = object : TypeToken<List<SectionEntity>>() {}.type
        return value.getModelFromJSON(type) ?: emptyList()
    }

}