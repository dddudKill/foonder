package com.example.foonder.domain.use_case.get_recipe

import com.example.foonder.common.Resource
import com.example.foonder.domain.models.RecipeDetail
import com.example.foonder.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(
    private val repository: RecipeRepository
) {
    operator fun invoke(id: Int): Flow<Resource<RecipeDetail>> {
        return repository.getRecipeById(id)
    }
}