package com.example.foonder.domain.use_case.get_recipes

import com.example.foonder.common.Resource
import com.example.foonder.domain.models.Recipe
import com.example.foonder.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetRecipesUseCase(
    private val repository: RecipeRepository
) {
    operator fun invoke(number: Int, tags: String?): Flow<Resource<List<Recipe>>> {
        return repository.getRecipes(number, tags)
    }
}