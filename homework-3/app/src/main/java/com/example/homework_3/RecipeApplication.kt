package com.example.homework_3

import android.app.Application

class RecipeApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ServiceLocator.initialize(this)
    }
}