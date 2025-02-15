package com.example.ramadanapp.features.home.data.mapper

import com.example.ramadanapp.common.data.mapper.Mapper
import com.example.ramadanapp.features.home.data.models.dto.HadithDto
import com.example.ramadanapp.features.home.data.models.dto.HomeResponseDto
import com.example.ramadanapp.features.home.data.models.dto.VideoDto
import com.example.ramadanapp.features.home.domain.models.Home

object HomeMapper : Mapper<HomeResponseDto, Home, Unit>() {
    override fun dtoToDomain(model: HomeResponseDto): Home {
        return Home(
            featuredVideo = model.featuredVideo?.let { VideoMapper.dtoToDomain(it) } ?: VideoMapper.dtoToDomain(VideoDto()),
            categories = model.categories?.map { CategoryMapper.dtoToDomain(it) } ?: emptyList(),
            hadith = model.hadith?.let { HadithMapper.dtoToDomain(it) } ?: HadithMapper.dtoToDomain(HadithDto())
        )
    }
}