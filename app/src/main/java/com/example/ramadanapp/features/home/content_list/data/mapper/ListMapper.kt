package com.example.ramadanapp.features.home.content_list.data.mapper

import com.example.ramadanapp.common.data.mapper.Mapper
import com.example.ramadanapp.features.home.content_list.data.models.VideoItemDto
import com.example.ramadanapp.features.home.content_list.domain.models.VideoItem
import com.example.ramadanapp.features.home.content_list.domain.models.VideoList

object ListMapper : Mapper<List<VideoItemDto>, VideoList, Unit>() {


    private fun VideoItemDto.toDomain(): VideoItem {
        return VideoItem(
            title = title.orEmpty(),
            url = url.orEmpty(),
            thumbnail = thumbnail.orEmpty(),
            videoId = videoId.orEmpty(),
            playlistId = playlistId.orEmpty()
        )
    }

    override fun dtoToDomain(model: List<VideoItemDto>): VideoList {
        return VideoList(
            videos = model.map { it.toDomain() }
        )
    }

}