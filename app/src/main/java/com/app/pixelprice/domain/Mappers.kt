package com.app.pixelprice.domain

import com.app.pixelprice.data.FavoriteGame
import com.app.pixelprice.data.Game

fun Game.toFavoriteGame(): FavoriteGame {
    return FavoriteGame(
        gameID = this.gameID,
        title = this.gameName,
        steamRatingText = this.steamRatingText,
        steamAppID = this.steamAppID,
        thumb = this.thumb
    )
}