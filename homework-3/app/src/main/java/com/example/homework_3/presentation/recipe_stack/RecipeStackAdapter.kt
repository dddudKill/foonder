package com.example.homework_3.presentation.recipe_stack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.homework_3.R
import com.example.homework_3.domain.models.recipes_by_ingredients.RecipeByIngredients
class RecipeStackAdapter : ListAdapter<RecipeByIngredients, RecipeStackAdapter.RecipeStackViewHolder>(RecipeStackDifferCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_card_layout, parent, false)
        return RecipeStackViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeStackViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class RecipeStackViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        protected val image = view.findViewById<ImageView>(R.id.recipe_image)
        protected val title = view.findViewById<TextView>(R.id.recipe_title)
        protected val doneTime = view.findViewById<TextView>(R.id.recipe_done_time)

        fun bind(recipe: RecipeByIngredients) {
            title.text = recipe.title
            Glide.with(view)
                .load(recipe.image)
                .override(Target.SIZE_ORIGINAL)
                .into(image)
        }
    }
}
