package com.example.foonder.data.repository

import com.example.foonder.common.Resource
import com.example.foonder.data.remote.RecipeSpoonacularApi
import com.example.foonder.domain.models.Recipe
import com.example.foonder.domain.models.RecipeDetail
import com.example.foonder.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RecipeRepositoryImpl (
    private val api: RecipeSpoonacularApi
) : RecipeRepository {

    override fun getRecipes(number: Int, tags: String?): Flow<Resource<List<Recipe>>> = flow {

        /* Caching Impl Should be here*/

        try {
            emit(Resource.Loading<List<Recipe>>())
            val recipes = api.getRecipes(number, tags).recipes.map { it.toRecipe() }
            emit(Resource.Success<List<Recipe>>(recipes))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Recipe>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Recipe>>("Couldn't reach server. Check your internet connection."))
        }
    }

    override fun getRecipeById(id: Int): Flow<Resource<RecipeDetail>> = flow {

        /* Caching Impl Should be here*/

        try {
            emit(Resource.Loading<RecipeDetail>())
            val recipe = api.getRecipeById(id).toRecipeDetail()
            emit(Resource.Success<RecipeDetail>(recipe))
        } catch (e: HttpException) {
            emit(Resource.Error<RecipeDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<RecipeDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}