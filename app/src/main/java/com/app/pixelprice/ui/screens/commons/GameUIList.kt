package com.app.pixelprice.ui.screens.commons

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.pixelprice.data.Game

@Composable
fun GameUIList(list: List<Game>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(list) { game ->
            GameUIItem(game)
        }
    }
}