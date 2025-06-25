package com.app.pixelprice.data

data class FavoriteGame(
    val gameID: String="",
    val title: String="",
    val thumb: String="",
    val steamAppID: String? = null,
    val steamRatingText: String? = null)