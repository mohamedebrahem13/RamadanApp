package com.example.ramadanapp.common.data.models

import androidx.annotation.StringRes

sealed class RamadanAppException(message: String?) : Exception(message) {

    sealed class Network(message: String? = null) : RamadanAppException(message) {
        data class Retrial(@StringRes val messageRes: Int, override val message: String?) :
            Network(message)

        data class Unhandled(@StringRes val messageRes: Int, override val message: String?) :
            Network(message)
    }

    sealed class Client(message: String? = null) : RamadanAppException(message) {
        data object Unauthorized : Client(message = "Unauthorized Access.") {
            private fun readResolve(): Any = Unauthorized
        }

        data class Unhandled(val httpErrorCode: Int, override val message: String?) : Client(
            message = "Unhandled client error with code: $httpErrorCode, and the failure reason: $message"
        )
    }

    sealed class Server(message: String? = null) : RamadanAppException(message) {
        data class InternalServerError(val httpErrorCode: Int, override val message: String?) :
            Server(message = "Internal server error with code: $httpErrorCode, and the failure reason: $message")
    }

    sealed class Local(message: String? = null) : RamadanAppException(message) {
        data class IOOperation(@StringRes val messageRes: Int, override val message: String? = "") :
            Local(message)
    }

    data class Unknown(override val message: String?) : RamadanAppException(message)

    fun isUnauthorized() = this == Client.Unauthorized
}