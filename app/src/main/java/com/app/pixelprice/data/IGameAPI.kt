package com.app.pixelprice.data

import retrofit2.http.GET
import retrofit2.http.Query

interface IGameAPI {
    @GET("deals")
    suspend fun getGameSearch(
        @Query("title") search: String
    ) : List<Game>

    @GET("deals")
    suspend fun getDealByID(
        @Query("id") dealID: String
    ) : GameDetailsResponse
}