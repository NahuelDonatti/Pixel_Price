package com.app.pixelprice.ui.screens.commons

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.pixelprice.data.Game
import com.app.pixelprice.data.Stores

@Composable
fun GameUIList(list: List<Game>,
               storesMap: Map<String, Stores>,
               modifier: Modifier = Modifier,
               onItemClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(list) { game ->
            val storeDetails = storesMap[game.storeID]
            GameUIItem(game, storeDetails,
                onItemClick = onItemClick)
        }
    }
}