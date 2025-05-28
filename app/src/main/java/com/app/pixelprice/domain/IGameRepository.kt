package com.app.pixelprice.domain

import com.app.pixelprice.data.Game

interface IGameRepository {
   suspend fun fetchGames(search: String) : List<Game>
}