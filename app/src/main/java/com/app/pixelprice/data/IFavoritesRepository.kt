package com.app.pixelprice.data

interface IFavoritesRepository {
    suspend fun isFavorite(gameId: String): Boolean
    suspend fun toggleFavorite(game: FavoriteGame): Boolean
    suspend fun getFavoriteGames(): List<FavoriteGame>
}