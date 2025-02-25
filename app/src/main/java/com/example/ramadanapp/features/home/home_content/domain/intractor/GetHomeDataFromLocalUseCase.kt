package com.example.ramadanapp.features.home.home_content.domain.intractor

import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import com.example.ramadanapp.features.home.home_content.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeDataFromLocalUseCase @Inject constructor(
    private val homeRepository: IHomeRepository
) {
    suspend operator fun invoke(): Flow<Resource<RamadanResponse>> = flow {
        emit(Resource.Progress(true)) // Emit loading state

        try {
            val localData = homeRepository.getHomeDataFromLocal()

            if (localData.sections.isNotEmpty() || localData.items.isNotEmpty()) {
                emit(Resource.Success(localData)) // Emit success state if data exists
            } else {
                emit(Resource.Success(RamadanResponse(emptyList(), emptyList()))) // Emit empty data state
            }
        } catch (e: Exception) {
            emit(Resource.Failure(e)) // Emit failure state
        }
    }
}