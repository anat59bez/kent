package com.kent.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KentApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize app-wide components
    }
}

