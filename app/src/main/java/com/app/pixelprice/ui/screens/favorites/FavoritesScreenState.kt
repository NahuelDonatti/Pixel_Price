package com.app.pixelprice.ui.screens.favorites

import com.app.pixelprice.data.FavoriteGame

data class FavoritesScreenState(
    val isLoading: Boolean = true,
    val favorites: List<FavoriteGame> = emptyList(),
    val error: String? = null
)