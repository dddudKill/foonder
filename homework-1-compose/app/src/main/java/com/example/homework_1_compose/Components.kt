package com.example.homework_1_compose

import android.widget.Toast
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
//import androidx.compose.foundation.gestures.ModifierLocalScrollableContainerProvider.value
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.homework_1_compose.ui.theme.SeaFoam


const val PORTRAIT_LAYOUT = 3
const val LANDSCAPE_LAYOUT = 4


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentGrid(count: Int, orientation: String, callback: (value: Int, coloValue: Int) -> Unit) {

    val itemsPerRow = if (orientation == PORTRAIT) PORTRAIT_LAYOUT else LANDSCAPE_LAYOUT

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
                NumberBox(number = index + 1, callback)
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
fun NumberBox(number: Int, callback: (value: Int, colorValue: Int) -> Unit) {

    val color = if (number % 2 == 1) Color.Blue else Color.Red

    val colorValue = if (color == Color.Blue) 0 else 1

    Box(
        modifier = Modifier
            .width(20.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(color)
            .clickable {
              callback(number, colorValue)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$number", color = Color.White, fontSize = 24.sp)
    }
}

private enum class ComponentState { Pressed, Released }

/*@Composable
fun NumberBox(number: Int, animated: Boolean = true) {
    var toState by remember {
        mutableStateOf(ComponentState.Released)
    }
    val modifier = Modifier.pointerInput(Unit) {
        detectTapGestures(onPress = {
            toState = ComponentState.Pressed
            tryAwaitRelease()
            toState = ComponentState.Released
        })
    }

    val transition: Transition<ComponentState> = updateTransition(targetState = toState, label = "")
    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 50f) }, label = ""
    ) { state ->
        if (state == ComponentState.Pressed) 1.2f else 1f
    }

    val color: Color by transition.animateColor(
        transitionSpec = {
            if (this.initialState == ComponentState.Pressed
                && this.targetState == ComponentState.Released
            ) {
                spring(stiffness = 50f)
            } else {
                tween(delayMillis = 150)
            }
        }, label = ""
    ) { state ->
        when (state) {
            ComponentState.Pressed -> MaterialTheme.colors.primary
            ComponentState.Released -> if (number % 2 == 1) Color.Blue else Color.Red
        }
    }
    Box(
        modifier = modifier
            .padding(top = 20.dp)
            .width(20.dp)
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .requiredSize((100 * scale).dp)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$number", color = Color.White, fontSize = 24.sp)
    }
}*/

@Composable
fun portraitContent(count: Int, callback: (value: Int, colorValue: Int) -> Unit): Int {

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
            ContentGrid(counter, PORTRAIT, callback)
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
fun landscapeContent(count: Int, callback: (value: Int, colorValue: Int) -> Unit): Int {
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
            ContentGrid(counter, LANDSCAPE, callback)
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

