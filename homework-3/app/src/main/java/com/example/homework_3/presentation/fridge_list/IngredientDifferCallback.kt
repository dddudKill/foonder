package com.example.homework_3.presentation.fridge_list

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient

class IngredientDifferCallback : DiffUtil.ItemCallback<ExtendedIngredient>() {
    override fun areItemsTheSame(oldItem: ExtendedIngredient, newItem: ExtendedIngredient): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ExtendedIngredient, newItem: ExtendedIngredient): Boolean {
        return oldItem.id == newItem.id
    }
}
