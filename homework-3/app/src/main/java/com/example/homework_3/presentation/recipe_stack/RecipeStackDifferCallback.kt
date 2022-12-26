package com.example.homework_3.presentation.recipe_stack

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_3.domain.models.recipes_by_ingredients.RecipeByIngredients

class RecipeStackDifferCallback : DiffUtil.ItemCallback<RecipeByIngredients>() {
    override fun areItemsTheSame(oldItem: RecipeByIngredients, newItem: RecipeByIngredients): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecipeByIngredients, newItem: RecipeByIngredients): Boolean {
        return oldItem.id == newItem.id
    }
}
