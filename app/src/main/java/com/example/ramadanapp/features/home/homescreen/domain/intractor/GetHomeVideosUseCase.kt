package com.example.ramadanapp.features.home.homescreen.domain.intractor

import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.features.home.homescreen.domain.models.Video
import com.example.ramadanapp.features.home.homescreen.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeVideosUseCase @Inject constructor(
    private val homeRepository: IHomeRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<Video>>> = flow {
        emit(Resource.loading()) // Emit loading state

        try {
            val videos = homeRepository.getHomeData()
            emit(Resource.success(videos)) // Emit success state
        } catch (e: Exception) {
            emit(Resource.failure(e)) // Emit failure state
        }
    }
}