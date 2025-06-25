package com.app.pixelprice.data.localdb

import com.app.pixelprice.data.GameDetailsResponse
import com.app.pixelprice.data.gameInfo

fun GameLocal.toExternal(): GameDetailsResponse {
    return GameDetailsResponse(
        gameInfo = gameInfo(
            gameID = this.gameID,
            storeID = this.storeID,
            name = this.gameName,
            steamAppID = this.steamAppID,
            steamRatingText = this.steamRatingText,
            salePrice = this.salePrice,
            retailPrice = this.retailPrice,
            releaseDate = this.releaseDate,
            thumb = this.thumb
        )
    )
}

fun gameInfo.toLocal(): GameLocal {
    return GameLocal(
        gameID = this.gameID,
        storeID = this.storeID,
        gameName = this.name,
        steamAppID = this.steamAppID,
        steamRatingText = this.steamRatingText,
        salePrice = this.salePrice,
        retailPrice = this.retailPrice,
        releaseDate = this.releaseDate,
        thumb = this.thumb
    )
}
