package com.app.pixelprice.data

interface IGameDataSource {
    suspend fun getGameList(search: String) : List<Game>
    suspend fun getGameByID(gameId: String) : GameDetailsResponse
}