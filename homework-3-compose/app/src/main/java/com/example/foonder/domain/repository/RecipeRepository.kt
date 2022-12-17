package com.example.foonder.domain.repository

import com.example.foonder.common.Resource
import com.example.foonder.domain.models.Recipe
import com.example.foonder.domain.models.RecipeDetail
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipes(number: Int, tags: String?): Flow<Resource<List<Recipe>>>

    fun getRecipeById(id: Int): Flow<Resource<RecipeDetail>>
}