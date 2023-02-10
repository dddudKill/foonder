package com.example.homework_3.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_3.R
import com.example.homework_3.presentation.step_stack.StepStackAdapter
import com.example.homework_3.presentation.step_stack.StepStackViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.coroutines.flow.collectLatest


class RecipeFragment : Fragment(), CardStackListener {

    private var recipeId: Int? = null
    private var recipeTitle: String? = null
    private val stepStackViewModel: StepStackViewModel by viewModels()
    private val stepStackAdapter = StepStackAdapter()
    private lateinit var stepStackLayoutManager: CardStackLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipeId = it.getInt(RECIPE_ID)
            recipeTitle = it.getString(RECIPE_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        val retryBtn = view.findViewById<Button>(R.id.btn_retry)
        retryBtn.setOnClickListener { getInstructions() }

        val bottomNavBar = view.findViewById<BottomNavigationView>(R.id.bottom_nav_bar)

        val title = view.findViewById<TextView>(R.id.recipe_title_instructions).apply {
            text = recipeTitle
        }

        stepStackLayoutManager = CardStackLayoutManager(context, this)
        stepStackLayoutManager.setVisibleCount(3)
        val stepStackView = view.findViewById<CardStackView>(R.id.step_card_stack).apply {
            layoutManager = stepStackLayoutManager
            adapter = stepStackAdapter
        }


        getInstructions()
        val state = stepStackViewModel.state
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            state.collectLatest { state ->
                if (state.isLoading) {
                    progressBar.visibility = View.VISIBLE
                }
                if (!state.isLoading && state.error.isBlank()) {
                    progressBar.visibility = View.GONE
                    retryBtn.visibility = View.GONE

                    stepStackAdapter.submitList(state.instruction!!.steps)
                }
                if (state.error.isNotBlank()) {
                    progressBar.visibility = View.GONE
                    retryBtn.visibility = View.VISIBLE
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getInstructions() {
        stepStackViewModel.getInstructions(recipeId!!)
    }


    override fun onCardDragging(direction: Direction?, ratio: Float) {}

    override fun onCardSwiped(direction: Direction?) {}

    override fun onCardRewound() {}

    override fun onCardCanceled() {}

    override fun onCardAppeared(view: View?, position: Int) {}

    override fun onCardDisappeared(view: View?, position: Int) {}

    companion object {

        private const val RECIPE_ID = "recipeId"
        private const val RECIPE_TITLE = "recipeTitle"

        @JvmStatic
        fun newInstance(recipeId: Int, recipeTitle: String) =
            RecipeFragment().apply {
                arguments = Bundle().apply {
                    putInt(RECIPE_ID, recipeId)
                    putString(RECIPE_TITLE, recipeTitle)
                }
            }
    }

}