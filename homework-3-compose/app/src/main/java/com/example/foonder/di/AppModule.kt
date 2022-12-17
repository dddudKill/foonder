package com.example.foonder.di

import com.example.foonder.common.Constants.BASE_URL
import com.example.foonder.data.remote.RecipeSpoonacularApi
import com.example.foonder.data.repository.RecipeRepositoryImpl
import com.example.foonder.domain.repository.RecipeRepository
import com.example.foonder.domain.use_case.get_recipes.GetRecipesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSpoonacularApi(): RecipeSpoonacularApi {
        return RecipeSpoonacularApi.create(BASE_URL)
    }


    @Provides
    @Singleton
    fun provideGetRecipesUseCase(repository: RecipeRepository): GetRecipesUseCase {
        return GetRecipesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(api: RecipeSpoonacularApi): RecipeRepository {
        return RecipeRepositoryImpl(api)
    }

}