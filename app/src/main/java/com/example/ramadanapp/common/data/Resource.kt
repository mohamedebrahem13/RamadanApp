package com.example.ramadanapp.common.data

import com.example.ramadanapp.common.data.models.RamadanAppException

sealed class Resource<out Model> {
    data class Progress<Model>(val loading: Boolean, val partialData: Model? = null) :
        Resource<Model>()

    data class Success<out Model>(val model: Model) : Resource<Model>()
    data class Failure(val exception: RamadanAppException) : Resource<Nothing>()

}