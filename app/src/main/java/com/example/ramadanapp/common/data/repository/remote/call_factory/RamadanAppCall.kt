package com.example.ramadanapp.common.data.repository.remote.call_factory

import com.example.ramadanapp.common.data.repository.remote.converter.IExceptionConverter
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RamadanAppCall(
    private val call: Call<ResponseBody>,
    private val exceptionConverter: IExceptionConverter
) : Call<ResponseBody> by call {

    override fun execute(): Response<ResponseBody> {
        throw UnsupportedOperationException("RamadanAppCall doesn't support execute()")
    }

    override fun enqueue(callback: Callback<ResponseBody>) {
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>, response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    callback.onResponse(call, response)
                } else {
                    val exception = exceptionConverter.convertResponseExceptions(
                        code = response.code(),
                        errorBody = response.errorBody()
                    )
                    callback.onFailure(call, exception)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, throwable: Throwable) {
                val exception = exceptionConverter.convertNetworkExceptions(throwable)
                callback.onFailure(call, exception)
            }
        })
    }
}