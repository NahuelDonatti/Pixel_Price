package com.app.pixelprice.ui.screens.commons

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.pixelprice.data.Game

@Composable
fun GameUIItem(game: Game, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        AsyncImage(
            model = game.thumb,
            contentDescription = game.gameName,
            contentScale = ContentScale.Crop
        )
        Text(
            text = game.gameName
        )
        Text(
            text = "Cheapest: $${game.cheapest}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}