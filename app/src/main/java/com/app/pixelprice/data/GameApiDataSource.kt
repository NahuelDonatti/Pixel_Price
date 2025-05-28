package com.app.pixelprice.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import android.util.Log

class GameApiDataSource() : IGameDataSource {

    override suspend fun getGameList(search: String): List<Game> {
        try{
            val gameResult = RetrofitInstance.gameAPI.getGameSearch(search)
            return gameResult
        } catch (e: Exception){
            Log.e("GameApiDataSource","Error recuperando la lista de juegos: ${e.message}", e)
            return emptyList()
        }
    }
}