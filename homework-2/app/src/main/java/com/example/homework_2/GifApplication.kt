package com.example.homework_2

import android.app.Application

class GifApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ServiceLocator.initialize(this)
    }
}