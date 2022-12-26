package com.example.homework_3

import android.annotation.SuppressLint
import android.content.Context
import com.example.homework_3.common.Constants
import com.example.homework_3.data.remote.RecipeSpoonacularApi
import com.example.homework_3.data.repository.RecipeRepositoryImpl


@SuppressLint("StaticFieldLeak")
object ServiceLocator {
    lateinit var context: Context

    private val accessor by lazy { RecipeSpoonacularApi.create(Constants.BASE_URL) }

    fun initialize(context: Context) {
        this.context = context.applicationContext
    }

    fun repository(): RecipeRepositoryImpl {
        return RecipeRepositoryImpl(accessor)
    }
}