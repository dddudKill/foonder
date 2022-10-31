package com.example.homework_1_compose

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.example.homework_1_compose.ui.theme.Homework1composeTheme

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
                    val countChanged = landscapeContent(count)
                    (activity as? FragmentToActivityCommunication)?.setCountOnMain(countChanged)
                }
            }
        }
        return view
    }
}
