package com.example.ramadanapp.common.data.repository.remote.call_factory
import com.example.ramadanapp.common.data.repository.remote.converter.IExceptionConverter
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class RamadanAppCallAdapterFactory(private val exceptionConverter: IExceptionConverter) :
    CallAdapter.Factory() {

    companion object {
        fun create(exceptionConverter: IExceptionConverter): RamadanAppCallAdapterFactory {
            return RamadanAppCallAdapterFactory(exceptionConverter)
        }
    }

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }
        val responseType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawType = getRawType(responseType)

        if (rawType != ResponseBody::class.java) {
            return null
        }

        return RamadanAppCallAdapter(exceptionConverter)
    }
}