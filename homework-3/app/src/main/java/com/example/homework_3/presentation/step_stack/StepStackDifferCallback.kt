package com.example.homework_3.presentation.step_stack

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_3.domain.models.analyzed_recipe_instructions.Step
import com.example.homework_3.domain.models.recipes_by_ingredients.RecipeByIngredients

class StepStackDifferCallback : DiffUtil.ItemCallback<Step>() {
    override fun areItemsTheSame(oldItem: Step, newItem: Step): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Step, newItem: Step): Boolean {
        return oldItem.step == newItem.step
    }
}
