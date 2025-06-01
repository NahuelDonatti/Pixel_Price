package com.app.pixelprice.data

import com.google.gson.annotations.SerializedName

data class Game(
    val gameID: String,
    val steamAppID: String?,
    val salePrice: String,
    @SerializedName("title")
    val gameName: String,
    val thumb: String
)

data class GameDetailsResponse(
    val info: GameInfo,
    val cheapestPriceEver: CheapestPriceEver?,
    val deals: List<Deal>
)

data class GameInfo(
    val title: String,
    val steamAppID: String?, // Can be null
    val thumb: String
)

data class CheapestPriceEver(
    val price: String,
    val date: Long
)

data class Deal(
    val storeID: String,
    val dealID: String,
    val price: String,
    val retailPrice: String,
    val savings: String
)

fun emptyGame() : GameDetailsResponse {
    return GameDetailsResponse(
        info = GameInfo(
            title = "",
            steamAppID = "",
            thumb = ""
        ),
        cheapestPriceEver = null,
        deals = emptyList()
    )
}