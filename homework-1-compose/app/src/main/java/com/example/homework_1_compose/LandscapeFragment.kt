package com.example.homework_1_compose

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import com.example.homework_1_compose.ui.theme.Homework1composeTheme
import androidx.activity.result.contract.ActivityResultContract as ActivityResultContract

fun createLandscapeFragment(
    field: Int
) : LandscapeFragment {
    return LandscapeFragment().apply {
        arguments = Bundle().apply { putInt("key", field) }
    }
}

class LandscapeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val count = arguments?.getInt("key")!!
        val view = inflater.inflate(R.layout.fragment_landscape, container, false).apply {
            findViewById<ComposeView>(R.id.compose_view_landscape).setContent {
                Homework1composeTheme {
                    val countChanged = landscapeContent(count) { value, colorValue ->
                        Toast.makeText(requireContext(), "$value", Toast.LENGTH_LONG) .show()
                    }
                    (activity as? FragmentToActivityCommunication)?.setCountOnMain(countChanged)
                }
            }
        }
        return view
    }
}