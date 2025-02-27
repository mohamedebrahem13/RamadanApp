package com.example.ramadanapp.common.data.repository.remote.converter

import com.example.ramadanapp.common.data.models.RamadanAppException
import okhttp3.ResponseBody

interface IExceptionConverter {
    fun convertNetworkExceptions(throwable: Throwable): RamadanAppException
    fun convertResponseExceptions(code: Int, errorBody: ResponseBody?): RamadanAppException
}