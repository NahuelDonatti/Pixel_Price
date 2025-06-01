package com.app.pixelprice.domain

import com.app.pixelprice.data.Game
import com.app.pixelprice.data.GameDetailsResponse

interface IGameRepository {
   suspend fun fetchGames(search: String) : List<Game>
   suspend fun fetchGame(gameID: String) : GameDetailsResponse
}