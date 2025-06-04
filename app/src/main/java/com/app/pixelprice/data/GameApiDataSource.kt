package com.app.pixelprice.data

import android.util.Log
import okio.IOException
import retrofit2.HttpException

class GameApiDataSource() : IGameDataSource {

    override suspend fun getGameList(search: String): List<Game> {
        try {
            val gameResult = RetrofitInstance.gameAPI.getGameSearch(search)
            return gameResult
        } catch (e: HttpException) {
            Log.e("GameApiDataSource", "Error de HTTP: ${e.message}", e)
            return emptyList()
        } catch (e: IOException) {
            Log.e("GameApiDataSource", "Error de internet: ${e.message}", e)
            return emptyList()
        } catch (e: Exception) {
            Log.e("GameApiDataSource", "Error recuperando la lista de juegos: ${e.message}", e)
            return emptyList()
        }
    }

    override suspend fun getDealByID(dealID: String): GameDetailsResponse {
            val game = RetrofitInstance.gameAPI.getDealByID(dealID)
            return game
    }
}

