package com.app.pixelprice.ui.screens.gamelist

import com.app.pixelprice.data.Game
import com.app.pixelprice.data.Stores

data class GameListScreenState (
    val gameList: List<Game> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val storesMap: Map<String, Stores> = emptyMap()

)