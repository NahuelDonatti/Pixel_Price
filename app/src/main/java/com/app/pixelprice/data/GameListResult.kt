package com.app.pixelprice.data

import com.google.gson.annotations.SerializedName

data class Game(
    val gameID: String,
    val steamAppID: String?,
    val cheapest: String,
    @SerializedName("external")
    val gameName: String,
    val thumb: String
)