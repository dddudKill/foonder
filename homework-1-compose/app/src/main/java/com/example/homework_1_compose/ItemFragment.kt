package com.example.homework_1_compose

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.example.homework_1_compose.ui.theme.Homework1composeTheme

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val number = arguments?.getInt(ARG_PARAM1)!!
        val colorValue = arguments?.getInt(ARG_PARAM2)!!
        val view = inflater.inflate(R.layout.fragment_item, container, false).apply {
            findViewById<ComposeView>(R.id.compose_view_item).setContent {
                Homework1composeTheme {
                    ItemFragmentCompose(number, colorValue)
                }
            }
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(number: Int, colorValue: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, number)
                    putInt(ARG_PARAM2, colorValue)
                }
            }
    }
}

@Composable
fun ItemFragmentCompose(number: Int, colorValue: Int) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
            .background(if (colorValue == 0) Color.Blue else Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("$number")
    }
}