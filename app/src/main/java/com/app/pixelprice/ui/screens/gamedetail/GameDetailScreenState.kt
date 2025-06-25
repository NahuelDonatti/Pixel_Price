package com.app.pixelprice.ui.screens.gamedetail

import com.app.pixelprice.data.GameDetailsResponse
import com.app.pixelprice.data.Stores
import com.app.pixelprice.data.emptyGame

data class GameDetailScreenState (
    val dealID: String = "",
    val gameDetail: GameDetailsResponse = emptyGame(),
    val store: Stores? = null,
    val isLoading: Boolean = false,
    val isFavorite: Boolean = false)
