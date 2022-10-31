package com.example.homework_1_compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework_1_compose.ui.theme.SeaFoam

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentGrid(count: Int, orientation: String) {

    val itemsPerRow = if (orientation == PORTRAIT) 3 else 4

    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(size = 16.dp))
            .background(SeaFoam),
    ){
        LazyVerticalGrid(
            cells = GridCells.Fixed(itemsPerRow),
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            items(count) { index ->
                NumberBox(number = index + 1)
            }
        }
    }
}


/*//Should not be used due to misunderstanding the task
@Composable
fun ContentColumn(count: Int, scope: RowScope) {
    with(scope) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(start = 8.dp, end = 8.dp)
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            LazyColumn(
                contentPadding = PaddingValues(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count) { index ->
                    NumberBox(number = index + 1)
                }
            }
        }
    }
}*/

@Composable
fun NumberBox(number: Int) {

    Box(
        modifier = Modifier
            .width(20.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(if (number % 2 == 1) Color.Blue else Color.Red),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$number", color = Color.White, fontSize = 24.sp)
    }
}

@Composable
fun portraitContent(count: Int): Int {

    var counter by remember {
        mutableStateOf(count)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(10f)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            ContentGrid(counter, PORTRAIT)
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
                    .fillMaxSize(),
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp
                )
            ) {
                Text(text = "Add", fontSize = 24.sp)
            }
        }
    }
    return counter
}

@Composable
fun landscapeContent(count: Int): Int {
    var counter by remember {
        mutableStateOf(count)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SeaFoam)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(6f),
        ) {
            ContentGrid(counter, LANDSCAPE)
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

