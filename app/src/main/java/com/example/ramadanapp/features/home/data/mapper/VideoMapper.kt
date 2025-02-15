package com.example.ramadanapp.features.home.data.mapper

import com.example.ramadanapp.common.data.mapper.Mapper
import com.example.ramadanapp.features.home.data.models.dto.VideoDto
import com.example.ramadanapp.features.home.domain.models.Video

object VideoMapper : Mapper<VideoDto, Video, Unit>() {
    override fun dtoToDomain(model: VideoDto): Video {
        return Video(
            id = model.id ?: 0,
            title = model.title.orEmpty(),
            thumbnail = model.thumbnail.orEmpty()
        )
    }
}