package com.example.homework_3.presentation.fridge_list

import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_3.R
import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient

class IngredientAdapter(val listener: OnItemCheckListener) : ListAdapter<ExtendedIngredient, IngredientAdapter.IngredientViewHolder>(IngredientDifferCallback()) {

    interface OnItemCheckListener {
        fun checkBoxStateChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item_layout, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



    inner class IngredientViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        protected val checkBox = view.findViewById<CheckBox>(R.id.ingredient_check_box)

        fun bind(ingredient: ExtendedIngredient) {
            checkBox.text = ingredient.name
            checkBox.isChecked = ingredient.isSelected
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                ingredient.isSelected = isChecked
                listener.checkBoxStateChanged()
            }
        }
    }
}
