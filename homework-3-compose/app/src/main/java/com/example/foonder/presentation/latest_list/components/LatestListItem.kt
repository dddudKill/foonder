package com.example.foonder.presentation.latest_list.components

import android.graphics.Color
import android.service.quicksettings.Tile
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foonder.R
import com.example.foonder.domain.models.Recipe
import com.example.foonder.ui.theme.Purple200
import okhttp3.internal.notify

@Preview
@Composable
fun LatestListItem(
    recipe: Recipe = Recipe(
        id = 12313,
        title = "Omlette very good",
        readyInMinutes = 30,
        servings = 3,
        image = "https://ketokotleta.ru/wp-content/uploads/6/c/b/6cb53f2131807e97c29c2d909e84371d.jpeg",
        summary = """
            Очень вкусный омлет, всем кушать помидоры омлет молоко ненавижу омлет 
            вкусно будет
            кушать
        """.trimIndent(),
        cuisines = listOf("Europe", "Russian"),
        dishTypes = listOf("Breakfest")
    ),
    onItemClick: (Recipe) -> Unit = {  }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(recipe) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${recipe.title}.",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "${recipe.id}.",
            color = Purple200,
            style = MaterialTheme.typography.body2,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview
@Composable
fun CategoryListItem(
    title: String = "Lactose",
    onItemClick: (String) -> Unit = {  }
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(title) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${title}",
            style = MaterialTheme.typography.h2,
            overflow = TextOverflow.Ellipsis
        )
        
        IconButton(
            onClick = {  }
        ) {
            Icon(
                painterResource(R.drawable.carret_right),
                "",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}