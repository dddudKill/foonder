package com.example.homework_3.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.*
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_3.R
import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient
import com.example.homework_3.presentation.recipe_stack.RecipeStackAdapter
import com.example.homework_3.presentation.recipe_stack.RecipeStackViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yuyakaido.android.cardstackview.*

class SwipeFragment : Fragment(), CardStackListener {

    private var ingredients: List<ExtendedIngredient>? = null

    private val recipeStackViewModel: RecipeStackViewModel by viewModels()
    private val recipeStackAdapter = RecipeStackAdapter()
    private lateinit var recipeStackLayoutManager: CardStackLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ingredients = Gson().fromJson(it.getString(INGREDIENTS), object : TypeToken<List<ExtendedIngredient>>() {}.type)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_swipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        val retryBtn = view.findViewById<Button>(R.id.btn_retry)
        retryBtn.setOnClickListener {
            recipeStackViewModel.getRecipes(ingredients!!)
        }

        val buttonContainer = view.findViewById<RelativeLayout>(R.id.button_container)


        val bottomNavBar = view.findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        bottomNavBar.visibility = View.VISIBLE
        bottomNavBar.menu[2].isEnabled = true

        recipeStackLayoutManager = CardStackLayoutManager(context, this)
        val recipeStackView = view.findViewById<CardStackView>(R.id.recipe_card_stack).apply {
            layoutManager = recipeStackLayoutManager
            adapter = recipeStackAdapter
        }


        val forkBtn = view.findViewById<FloatingActionButton>(R.id.fork_button)
        forkBtn.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            recipeStackLayoutManager.setSwipeAnimationSetting(setting)
            recipeStackView.swipe()
        }

        val rewindBtn = view.findViewById<FloatingActionButton>(R.id.rewind_button)
        rewindBtn.setOnClickListener {
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            recipeStackLayoutManager.setRewindAnimationSetting(setting)
            recipeStackView.rewind()
        }

        val skipBtn = view.findViewById<FloatingActionButton>(R.id.skip_button)
        skipBtn.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            recipeStackLayoutManager.setSwipeAnimationSetting(setting)
            recipeStackView.swipe()
        }


        val state = recipeStackViewModel.state
        recipeStackViewModel.getRecipes(ingredients!!)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            state.collect { state ->
                if (state.isLoading) {
                    progressBar.visibility = View.VISIBLE
                    retryBtn.visibility = View.GONE
                }
                if (!state.isLoading && state.error.isBlank()) {
                    progressBar.visibility = View.GONE
                    retryBtn.visibility = View.GONE
                    recipeStackView.visibility = View.VISIBLE
                    buttonContainer.visibility = View.VISIBLE

                    recipeStackAdapter.submitList(state.recipes)
                }
                if (state.error.isNotBlank()) {
                    progressBar.visibility = View.GONE
                    retryBtn.visibility = View.VISIBLE
                    recipeStackView.visibility = View.GONE
                    buttonContainer.visibility = View.GONE
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardSwiped(direction: Direction?) {
        if (direction == Direction.Right) {
            val recipe = recipeStackAdapter.currentList[recipeStackLayoutManager.topPosition - 1]
            activity?.supportFragmentManager?.let {
                val transaction = it.beginTransaction()
                transaction
                    .replace(R.id.fragmentContainer, RecipeFragment.newInstance(recipe.id, recipe.title))
                    .addToBackStack(null)
                    .commit()
            }
        }
        // PAGING to be implemented
        else if (recipeStackLayoutManager.topPosition == recipeStackAdapter.itemCount) {
            recipeStackViewModel.getRecipes(ingredients!!, 12)
        }
    }

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {}

    companion object {
        private const val INGREDIENTS = "ingredients"
        private const val TAGS = "tags"

        @JvmStatic
        fun newInstance(ingredients: Set<ExtendedIngredient>) =
            SwipeFragment().apply {
                arguments = Bundle().apply {
                    putString(INGREDIENTS, Gson().toJson(ingredients))
                }
            }
    }
}