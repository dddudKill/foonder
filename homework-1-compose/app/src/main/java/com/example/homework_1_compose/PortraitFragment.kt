package com.example.homework_1_compose

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

fun createPortraitFragment(
    field: Int
) : PortraitFragment {
    return PortraitFragment().apply {
        arguments = Bundle().apply { putInt("key", field) }
    }
}

class PortraitFragment : Fragment() {

    var count = INITIAL_COUNT

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var count = arguments?.getInt("key")!!
        val view = inflater.inflate(R.layout.fragment_portrait, container, false).apply {
            findViewById<ComposeView>(R.id.compose_view_portrait).setContent {
                Homework1composeTheme {
                    count = PortraitContent(count)
                    (activity as? FragmentToActivityCommunication)?.setCountOnMain(count)
                }
            }
        }
        return view
    }
}

@Composable
fun PortraitContent(count: Int): Int {
    var counter by remember {
        mutableStateOf(count)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SeaFoam)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .weight(10f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ContentColumn(count = counter, this)
            ContentColumn(count = counter, this)
            ContentColumn(count = counter, this)
        }
        Box(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth()
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