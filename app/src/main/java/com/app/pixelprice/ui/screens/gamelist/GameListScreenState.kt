package com.app.pixelprice.ui.screens.gamelist

import com.app.pixelprice.data.Game

data class GameListScreenState (
    val gameList: List<Game> = emptyList(),
    val searchQuery: String = ""
)