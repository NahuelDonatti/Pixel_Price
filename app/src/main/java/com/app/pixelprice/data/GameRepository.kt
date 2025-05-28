package com.app.pixelprice.data

import com.app.pixelprice.domain.IGameRepository

class GameRepository (val gameDataSource: IGameDataSource = GameApiDataSource()) : IGameRepository{


    override suspend fun fetchGames(search: String): List<Game>{
        return gameDataSource.getGameList(search)
    }
}