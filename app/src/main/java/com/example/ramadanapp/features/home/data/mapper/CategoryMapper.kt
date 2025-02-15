package com.example.ramadanapp.features.home.data.mapper

import com.example.ramadanapp.common.data.mapper.Mapper
import com.example.ramadanapp.features.home.data.models.dto.CategoryDto
import com.example.ramadanapp.features.home.domain.models.Category

object CategoryMapper : Mapper<CategoryDto, Category, Unit>() {
    override fun dtoToDomain(model: CategoryDto): Category {
        return Category(
            id = model.id ?: 0,
            name = model.name.orEmpty(),
            videos = model.videos?.map { VideoMapper.dtoToDomain(it) } ?: emptyList()
        )
    }
}