package com.example.ramadanapp.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RamadanApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}