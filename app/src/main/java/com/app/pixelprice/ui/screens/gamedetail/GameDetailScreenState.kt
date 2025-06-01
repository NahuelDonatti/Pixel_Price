package com.app.pixelprice.ui.screens.gamedetail

import com.app.pixelprice.data.GameDetailsResponse
import com.app.pixelprice.data.emptyGame

data class GameDetailScreenState (val gameID: String = "0", val gameDetail: GameDetailsResponse = emptyGame())
