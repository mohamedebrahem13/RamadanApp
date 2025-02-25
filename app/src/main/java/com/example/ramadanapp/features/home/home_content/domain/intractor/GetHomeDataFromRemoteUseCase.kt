package com.example.ramadanapp.features.home.home_content.domain.intractor

import android.util.Log
import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.features.home.home_content.domain.models.RamadanResponse
import com.example.ramadanapp.features.home.home_content.domain.repository.IHomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeDataFromRemoteUseCase @Inject constructor(
    private val homeRepository: IHomeRepository
) {
    suspend operator fun invoke(): Flow<Resource<RamadanResponse>> = flow {
        emit(Resource.loading()) // Emit loading state
        try {
            val response  = homeRepository.getHomeDataFromRemote()
            Log.d("TAG", "invoke: $response")
            homeRepository.saveRamadanResponse(response)
            emit(Resource.success(response)) // Emit success state
        } catch (e: Exception) {
            emit(Resource.failure(e)) // Emit failure state
        }
    }
}