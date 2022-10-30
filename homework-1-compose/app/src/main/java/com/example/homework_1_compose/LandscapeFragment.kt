package com.example.homework_1_compose

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework_1_compose.ui.theme.Homework1composeTheme
import com.example.homework_1_compose.ui.theme.SeaFoam

fun createLandscapeFragment(
    field: Int
) : LandscapeFragment {
    return LandscapeFragment().apply {
        arguments = Bundle().apply { putInt("key", field) }
    }
}

class LandscapeFragment : Fragment() {

    var count = INITIAL_COUNT

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        count = arguments?.getInt("key")!!
        val view = inflater.inflate(R.layout.fragment_landscape, container, false).apply {
            findViewById<ComposeView>(R.id.compose_view_landscape).setContent {
                Homework1composeTheme {
                    count = LandscapeContent(count)
                    (activity as? FragmentToActivityCommunication)?.setCountOnMain(count)
                }
            }
        }
        return view
    }
}

@Composable
fun LandscapeContent(count: Int): Int {
    var counter by remember {
        mutableStateOf(count)
    }
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(SeaFoam)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .weight(8f),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ContentColumn(count = counter, this)
            ContentColumn(count = counter, this)
            ContentColumn(count = counter, this)
            ContentColumn(count = counter, this)
        }
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { counter++ },
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = "Add", fontSize = 24.sp)
            }
        }
    }
    return counter
}