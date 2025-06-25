package com.app.pixelprice.data.localdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("games")
data class GameLocal (
    val storeID: String = "",
    @PrimaryKey val gameID: String = "",
    val gameName: String = "",
    val steamAppID: String? = "",
    val steamRatingText: String? = "",
    val salePrice: String = "",
    val retailPrice: String = "",
    val releaseDate: Long? = 0,
    val thumb: String = ""
)

