package com.example.homework_1_compose

import android.app.Fragment
import android.content.ClipData.Item
import android.content.res.Configuration
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.FragmentManager
import com.example.homework_1_compose.ui.theme.Homework1composeTheme

fun createPortraitFragment(
    field: Int
) : PortraitFragment {
    return PortraitFragment().apply {
        arguments = Bundle().apply { putInt("key", field) }
    }
}

class PortraitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val count = arguments?.getInt("key")?: 0
        val view = inflater.inflate(R.layout.fragment_portrait, container, false).apply {
            findViewById<ComposeView>(R.id.compose_view_portrait).setContent {
                Homework1composeTheme {
                    val countChanged = portraitContent(count) { value, colorValue ->
                        val fragmentManager = parentFragmentManager
                        val transaction = fragmentManager.beginTransaction()
                        transaction.replace(R.id.container, ItemFragment.newInstance(value, colorValue)).addToBackStack(null).commit()
                    }
                    (activity as? FragmentToActivityCommunication)?.setCountOnMain(countChanged)
                }
            }
        }
        return view
    }
}

