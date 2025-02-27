package com.example.ramadanapp.common.data.repository.remote.converter

import com.example.ramadanapp.R
import com.example.ramadanapp.common.data.models.RamadanAppException
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ExceptionConverter : IExceptionConverter {
    override fun convertNetworkExceptions(throwable: Throwable): RamadanAppException {
        return when (throwable) {
            is SocketTimeoutException, is UnknownHostException, is IOException -> RamadanAppException.Network.Retrial(
                messageRes = R.string.network_connection_error,
                message = "Retrial network error."
            )
            is JsonSyntaxException ->
                RamadanAppException.Local.IOOperation(
                    messageRes = R.string.parsing_error,
                    message = "Invalid JSON format. Please check the data structure."
                )

            else -> RamadanAppException.Network.Unhandled(
                messageRes = R.string.unknown_network_error,
                message = "NetworkException Unhandled error."
            )
        }
    }

    override fun convertResponseExceptions(
        code: Int, errorBody: ResponseBody?
    ): RamadanAppException {
        return when (code) {
            401 -> RamadanAppException.Client.Unauthorized

            in 400..499 -> RamadanAppException.Client.Unhandled(
                httpErrorCode = code,
                message = errorBody?.string() ?: "Client error without message."
            )

            in 500..599 -> RamadanAppException.Server.InternalServerError(
                httpErrorCode = code,
                message = errorBody?.string()
            )

            else -> RamadanAppException.Client.Unhandled(
                httpErrorCode = code,
                message = errorBody?.string()
            )
        }
    }
}