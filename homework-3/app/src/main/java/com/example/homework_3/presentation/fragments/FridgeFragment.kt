package com.example.homework_3.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_3.R
import com.example.homework_3.domain.models.recipe_information.ExtendedIngredient
import com.example.homework_3.presentation.fridge_list.AisleAdapter
import com.example.homework_3.presentation.fridge_list.AisleListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.collectLatest


class FridgeFragment : Fragment() {

    private var isAuthorized: Boolean = false

    private val ingredientCategoriesViewModel: AisleListViewModel by viewModels()
    private val aisleAdapter = AisleAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isAuthorized = it.getBoolean(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_fridge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)

        val textWelcomeDescription = view.findViewById<TextView>(R.id.welcome_text_description)
        textWelcomeDescription.text = if (isAuthorized) "Укажите продукты" else textWelcomeDescription.text

        val retryBtn = view.findViewById<Button>(R.id.btn_retry)
        retryBtn.setOnClickListener { getAisles() }

        val searchBar = view.findViewById<CardView>(R.id.search_bar)

        val proceedBtn = view.findViewById<Button>(R.id.btn_proceed)

        val bottomNavBar = view.findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        if (isAuthorized) bottomNavBar.visibility = View.VISIBLE

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerCategory).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = aisleAdapter
        }



        proceedBtn.setOnClickListener {
            val ingredients: Set<ExtendedIngredient> = buildSet {
                for (aisle in aisleAdapter.currentList) {
                    for (ingredient in aisle.second) {
                        if (ingredient.isSelected) add(ingredient)
                    }
                }
            }

            if (ingredients.isNotEmpty()) {
                activity?.supportFragmentManager?.let {
                    val transaction = it.beginTransaction()
                    transaction
                        .replace(R.id.fragmentContainer, SwipeFragment.newInstance(ingredients))
                        .addToBackStack(null)
                        .commit()
                }
            } else {
                Toast.makeText(context, "No ingredients selected", Toast.LENGTH_SHORT).show()
            }
        }

        val state = ingredientCategoriesViewModel.state
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            state.collectLatest { state ->
                if (state.isLoading) {
                    progressBar.visibility = View.VISIBLE
                }
                if (!state.isLoading && state.error.isBlank()) {
                    progressBar.visibility = View.GONE
                    retryBtn.visibility = View.GONE

                    recyclerView.visibility = View.VISIBLE
                    searchBar.visibility = View.VISIBLE
                    proceedBtn.visibility = View.VISIBLE

                    aisleAdapter.submitList(state.aisles.toList())
                }
                if (state.error.isNotBlank()) {
                    progressBar.visibility = View.GONE
                    retryBtn.visibility = View.VISIBLE
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getAisles() {
        ingredientCategoriesViewModel.getAisles()
    }


    companion object {
        private const val ARG_PARAM1 = "isAuthorized"

        @JvmStatic
        fun newInstance(isAuthorized: Boolean) =
            FridgeFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_PARAM1, isAuthorized)
                }
            }
    }
}