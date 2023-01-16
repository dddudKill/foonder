package com.example.homework_3.presentation.step_stack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.homework_3.R
import com.example.homework_3.common.Constants
import com.example.homework_3.domain.models.analyzed_recipe_instructions.Step
import com.example.homework_3.domain.models.recipes_by_ingredients.RecipeByIngredients
import com.example.homework_3.presentation.recipe_stack.RecipeStackAdapter

class StepStackAdapter : ListAdapter<Step, StepStackAdapter.StepStackViewHolder>(StepStackDifferCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepStackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.step_card_layout, parent, false)
        return StepStackViewHolder(view)
    }

    override fun onBindViewHolder(holder: StepStackViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class StepStackViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        protected val image = view.findViewById<ImageView>(R.id.step_image)
        protected val title = view.findViewById<TextView>(R.id.step_title)
        protected val description = view.findViewById<TextView>(R.id.step_description)

        fun bind(step: Step) {
            title.text = view.context.getString(R.string.step_text, step.number)

            val imageUrl = if (step.ingredients.isNotEmpty())
                Constants.INGREDIENT_IMAGE_URL + step.ingredients.last().image
            else if (step.equipment.isNotEmpty())
                Constants.EQUIPMENT_IMAGE_URL + step.equipment.first().image
            else
                Constants.INGREDIENT_IMAGE_URL + "apple.png"
            Glide.with(view)
                .load(imageUrl)
                .override(Target.SIZE_ORIGINAL)
                .into(image)

            description.text = step.step
        }
    }
}
