package com.example.ramadanapp.features.home.data.repository.remote

import com.example.ramadanapp.common.domain.repository.remote.INetworkProvider
import com.example.ramadanapp.features.home.data.models.dto.CategoryDto
import com.example.ramadanapp.features.home.data.models.dto.HadithDto
import com.example.ramadanapp.features.home.data.models.dto.HomeResponseDto
import com.example.ramadanapp.features.home.data.models.dto.VideoDto
import com.example.ramadanapp.features.home.domain.repository.remote.IHomeRemoteDS
import javax.inject.Inject

class HomeRemoteDS @Inject constructor(
    private val networkProvider: INetworkProvider
) : IHomeRemoteDS {

    override suspend fun fetchHomeData(): HomeResponseDto {
        return HomeResponseDto(
            featuredVideo = VideoDto(
                id = 101,
                title = "الرقية الشرعية الإسلامية",
                thumbnail = "https://example.com/featured_video.jpg"
            ),
            categories = listOf(
                CategoryDto(
                    id = 10,
                    name = "المفضلة",
                    videos = listOf(
                        VideoDto(
                            id = 201,
                            title = "أذكار الصباح والمساء",
                            thumbnail = "https://example.com/video1.jpg"
                        ),
                        VideoDto(
                            id = 202,
                            title = "دعاء القنوت",
                            thumbnail = "https://example.com/video2.jpg"
                        )
                    )
                ),
                CategoryDto(
                    id = 11,
                    name = "الأحاديث النبوية",
                    videos = listOf(
                        VideoDto(
                            id = 301,
                            title = "حديث عن الصدقة",
                            thumbnail = "https://example.com/video3.jpg"
                        )
                    )
                )
            ),
            hadith = HadithDto(
                id = 401,
                text = "إنما الأعمال بالنيات، وإنما لكل امرئ ما نوى.",
                reference = "صحيح البخاري"
            )
        )
    }
}