package com.example.homework_3.presentation.fridge_list

import androidx.recyclerview.widget.DiffUtil
import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient
import com.example.homework_3.domain.models.recipes_by_ingredients.Aisle

class AisleDifferCallback : DiffUtil.ItemCallback<Pair<String, List<ExtendedIngredient>>>() {
    override fun areItemsTheSame(oldItem: Pair<String, List<ExtendedIngredient>>, newItem: Pair<String, List<ExtendedIngredient>>): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pair<String, List<ExtendedIngredient>>, newItem: Pair<String, List<ExtendedIngredient>>): Boolean {
        return oldItem.first == newItem.first
    }
}
