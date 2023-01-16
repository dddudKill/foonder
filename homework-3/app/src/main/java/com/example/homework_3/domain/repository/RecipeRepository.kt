package com.example.homework_3.domain.repository

import com.example.homework_3.common.Resource
import com.example.homework_3.domain.models.analyzed_recipe_instructions.RecipeInstruction
import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient
import com.example.homework_3.domain.models.recipe_information.Recipe
import com.example.homework_3.domain.models.recipe_information.RecipeDetail
import com.example.homework_3.domain.models.recipes_by_ingredients.Aisle
import com.example.homework_3.domain.models.recipes_by_ingredients.RecipeByIngredients
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipes(number: Int, tags: String?): Flow<Resource<List<Recipe>>>

    fun getRecipeById(id: Int): Flow<Resource<RecipeDetail>>

    fun getAisles(): Flow<Resource<Map<String, List<ExtendedIngredient>>>>

    fun getRecipesByIngredients(ingredients: List<ExtendedIngredient>, number: Int?): Flow<Resource<List<RecipeByIngredients>>>

    fun getRecipeAnalyzedInstructions(id: Int): Flow<Resource<RecipeInstruction>>
}