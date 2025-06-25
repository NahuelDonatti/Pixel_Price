package com.app.pixelprice.data

import com.google.gson.annotations.SerializedName

data class Game(
    val dealID: String = "",
    val gameID: String = "",
    val steamAppID: String? = "",
    val steamRatingText: String? = "",
    val salePrice: String = "",
    val normalPrice: String = "",
    val isOnSale: String = "",
    val savings: String = "",
    val releaseDate: Long? = 0,
    @SerializedName("title")
    val gameName: String = "",
    val thumb: String = "",
    val storeID: String = ""
)

data class GameDetailsResponse(
    val gameInfo: gameInfo = gameInfo()
)

data class gameInfo(
    val storeID: String = "",
    val gameID: String = "",
    val name: String = "",
    val steamAppID: String? = "",
    val steamRatingText: String? = "",
    val salePrice: String = "",
    val retailPrice: String = "",
    val releaseDate: Long? = 0,
    val thumb: String = ""
)


fun emptyGame() : GameDetailsResponse{
    return GameDetailsResponse(gameInfo = gameInfo(
        storeID = "0",
        gameID = "0",
        name = "",
        steamAppID = null,
        steamRatingText = null,
        salePrice = "0",
        retailPrice = "0",
        releaseDate = null,
        thumb = "0",
    ))
}