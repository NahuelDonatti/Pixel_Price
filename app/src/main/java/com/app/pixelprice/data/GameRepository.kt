package com.app.pixelprice.data

import android.util.Log
import com.app.pixelprice.domain.IGameRepository

class GameRepository (val gameDataSource: IGameDataSource = GameApiDataSource()) : IGameRepository{


    override suspend fun fetchGames(search: String): List<Game>{
        Log.d("GameRepository", "fetchGames - Query recibida desde ViewModel: $search")

        return if (search.startsWith("sortBy=")) {
            // Caso para ofertas ordenadas (ej. "sortBy=Price")
            val sortByParam = search.split("=").getOrElse(1) { "" } // Obtiene "Price"
            Log.d("GameRepository", "fetchGames - Solicitando ofertas ordenadas por: $sortByParam")
            gameDataSource.getDealsSorted(sortByParam)

    } else {
            gameDataSource.getGameList(search)
        }
        }

        override suspend fun fetchGame(dealID: String): GameDetailsResponse {
        return gameDataSource.getDealByID(dealID)
    }
}