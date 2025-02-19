package com.example.ramadanapp.features.home.home_content.data.mapper

import com.example.ramadanapp.common.data.mapper.Mapper
import com.example.ramadanapp.features.home.home_content.data.models.dto.VideoItemDto
import com.example.ramadanapp.features.home.home_content.data.models.dto.VideoResponseDto
import com.example.ramadanapp.features.home.home_content.domain.models.Video

object VideoMapper : Mapper<VideoItemDto, Video, Unit>() {
    override fun dtoToDomain(model: VideoItemDto): Video {
        return Video(
            subCategory = model.subCategory.orEmpty(),
            category = model.category.orEmpty(),
            title = model.title.orEmpty(),
            url = model.url.orEmpty()
        )
    }
    fun dtoListToDomainList(videoResponseDto: VideoResponseDto?): List<Video> {
        return videoResponseDto?.data?.map { dtoToDomain(it) } ?: emptyList()
    }

}
