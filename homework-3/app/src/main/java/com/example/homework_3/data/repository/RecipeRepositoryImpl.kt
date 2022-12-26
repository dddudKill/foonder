package com.example.homework_3.data.repository

import com.example.homework_3.common.Resource
import com.example.homework_3.data.remote.RecipeSpoonacularApi
import com.example.homework_3.domain.models.analyzed_recipe_instructions.RecipeInstruction
import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient
import com.example.homework_3.domain.models.recipe_information.Recipe
import com.example.homework_3.domain.models.recipe_information.RecipeDetail
import com.example.homework_3.domain.models.recipes_by_ingredients.RecipeByIngredients
import com.example.homework_3.domain.repository.RecipeRepository


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RecipeRepositoryImpl(
    private val api: RecipeSpoonacularApi
) : RecipeRepository {

    override fun getRecipes(number: Int, tags: String?): Flow<Resource<List<Recipe>>> = flow {

        /* Caching Impl Should be here*/

        try {
            emit(Resource.Loading<List<Recipe>>())
            val recipes = api.getRecipes(number, tags).recipes.map { it.toRecipe() }
            emit(Resource.Success<List<Recipe>>(recipes))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Recipe>>(e.localizedMessage ?: "An unexpected error occurred"))
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
            emit(Resource.Error<RecipeDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<RecipeDetail>("Couldn't reach server. Check your internet connection."))
        }
    }


    override fun getAisles(): Flow<Resource<Map<String, List<ExtendedIngredient>>>> = flow {
        try {
            emit(Resource.Loading<Map<String, List<ExtendedIngredient>>>())
            val recipes = api.getRecipes(4, tags = null).recipes.map { it.toRecipe() }
            val ingredients = buildSet<ExtendedIngredient> {
                for (recipe in recipes)
                    recipe.extendedIngredients.forEach { add(it) }
            }
            val aisles = ingredients.groupBy { it.aisle }
            emit(Resource.Success<Map<String, List<ExtendedIngredient>>>(aisles))
        } catch (e: HttpException) {
            emit(Resource.Error<Map<String, List<ExtendedIngredient>>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error<Map<String, List<ExtendedIngredient>>>("Couldn't reach server. Check your internet connection."))
        }
    }

    override fun getRecipesByIngredients(ingredients: List<ExtendedIngredient>): Flow<Resource<List<RecipeByIngredients>>> = flow {
        try {
            emit(Resource.Loading<List<RecipeByIngredients>>())
            val recipes = api.getRecipesByIngredients(ingredients.map { it.name }).map { it.toRecipeByIngredients() }
            emit(Resource.Success<List<RecipeByIngredients>>(recipes))
        } catch (e: HttpException) {
            emit(Resource.Error<List<RecipeByIngredients>>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error<List<RecipeByIngredients>>("Couldn't reach server. Check your internet connection."))
        }
    }

    override fun getRecipeAnalyzedInstructions(id: Int): Flow<Resource<RecipeInstruction>> = flow {
        try {
            emit(Resource.Loading<RecipeInstruction>())
            val instruction = api.getRecipeAnalyzedInstructions(id)//.first().toRecipeInstruction()
            emit(Resource.Success<RecipeInstruction>(instruction.first().toRecipeInstruction()))
        } catch (e: HttpException) {
            emit(Resource.Error<RecipeInstruction>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: Exception) {
            emit(Resource.Error<RecipeInstruction>("Couldn't reach server. Check your internet connection."))
        }
    }
}