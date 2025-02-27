package com.example.ramadanapp.common.domain.intractor

import com.example.ramadanapp.common.data.Resource
import com.example.ramadanapp.common.data.models.RamadanAppException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseUseCase<out Model, in Params> {

    abstract suspend fun execute(params: Params? = null): Model

    operator fun invoke(
        scope: CoroutineScope,
        params: Params? = null,
        onResult: (Resource<Model>) -> Unit
    ) {
        scope.launch(Dispatchers.Main) {
            onResult.invoke(Resource.loading())
            try {
                withContext(Dispatchers.IO) {
                    val result = execute(params)
                    onResult.invoke(Resource.success(result))
                }
                withContext(Dispatchers.Main) {
                    onResult.invoke(Resource.loading(false))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.IO) {
                    val failureResource = handleError(e)

                    onResult.invoke(Resource.failure(failureResource))
                }

                withContext(Dispatchers.Main) {
                    onResult.invoke(Resource.loading(false))
                }
            }
        }
    }
    operator fun invoke(params: Params? = null): Flow<Resource<Model>> = channelFlow {
        send(Resource.loading())
        try {
            val result = execute(params)
            send(Resource.success(result))
            send(Resource.loading(false))
        } catch (e: Exception) {
            val failureResource = handleError(e)

            send(Resource.failure(failureResource))
            send(Resource.loading(false))
        }
    }.flowOn(Dispatchers.IO)


    private fun handleError(e: Exception): RamadanAppException {
        return if (e is RamadanAppException) e else RamadanAppException.Unknown("Unknown error appeared")
    }
}