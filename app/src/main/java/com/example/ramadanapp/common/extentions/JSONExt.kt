package com.example.ramadanapp.common.extentions


import com.google.gson.Gson
import java.lang.reflect.Type

fun <M> String.getModelFromJSON(tokenType: Type): M = Gson().fromJson(this, tokenType)
fun <M> M.toJson(): String = Gson().toJson(this)