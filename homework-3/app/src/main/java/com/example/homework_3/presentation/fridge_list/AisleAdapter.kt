package com.example.homework_3.presentation.fridge_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_3.R
import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient

class AisleAdapter : ListAdapter<Pair<String, List<ExtendedIngredient>>, AisleAdapter.AisleViewHolder>(AisleDifferCallback()) {

    companion object {
        const val CARRET_EXPANDED = 90F
        const val CARRET_COLLAPSED = 0F
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AisleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.aisle_item_layout, parent, false)
        return AisleViewHolder(view)
    }

    override fun onBindViewHolder(holder: AisleViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class AisleViewHolder(val view: View) : RecyclerView.ViewHolder(view), IngredientAdapter.OnItemCheckListener {
        protected val value = view.findViewById<TextView>(R.id.category_name)
        protected val carret = view.findViewById<ImageView>(R.id.carret)
        protected val selectedMark = view.findViewById<ImageView>(R.id.selected_mark)

        val ingredientAdapter = IngredientAdapter(this)
        protected val ingredientList = view.findViewById<RecyclerView>(R.id.ingredients_recycler).apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = ingredientAdapter
        }

        fun bind(aisle: Pair<String, List<ExtendedIngredient>>) {
            value.text = aisle.first
            selectedMark.visibility = if (aisle.second.any { it.isSelected }) View.VISIBLE else View.GONE
            ingredientAdapter.submitList(aisle.second)
            view.setOnClickListener {
                ingredientList.visibility = if (ingredientList.visibility == View.GONE) View.VISIBLE else View.GONE
                carret.rotation = if (ingredientList.visibility == View.VISIBLE) CARRET_EXPANDED else CARRET_COLLAPSED
                selectedMark.visibility = if (aisle.second.any { it.isSelected }) View.VISIBLE else View.GONE
            }
        }

        override fun checkBoxStateChanged() {
            notifyItemChanged(bindingAdapterPosition)
        }
    }
}
