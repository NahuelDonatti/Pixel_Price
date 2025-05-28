package com.app.pixelprice.data

import retrofit2.http.GET
import retrofit2.http.Query

interface IGameAPI {
    @GET("games")
    suspend fun getGameSearch(
        @Query("title") search: String
    ) : List<Game>
}