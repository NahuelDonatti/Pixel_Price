package com.app.pixelprice.data

interface IGameDataSource {
    suspend fun getGameList(search: String) : List<Game>
}