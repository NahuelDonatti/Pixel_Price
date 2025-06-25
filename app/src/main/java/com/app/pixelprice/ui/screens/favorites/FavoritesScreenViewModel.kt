package com.app.pixelprice.ui.screens.favorites

import com.app.pixelprice.data.FavoriteGame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pixelprice.data.FavoritesRepository
import com.app.pixelprice.data.IFavoritesRepository
import kotlinx.coroutines.launch

class FavoritesScreenViewModel(
    private val favoritesRepository: IFavoritesRepository = FavoritesRepository()
) : ViewModel() {

    var uiState by mutableStateOf(FavoritesScreenState())
        private set

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            try {
                val favoriteGames = favoritesRepository.getFavoriteGames()
                uiState = uiState.copy(isLoading = false, favorites = favoriteGames)
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    error = "Debes iniciar sesión para ver tus favoritos."
                )
            }
        }
    }

    fun removeFavorite(game: FavoriteGame) {
        viewModelScope.launch {
            val currentList = uiState.favorites.toMutableList()
            currentList.remove(game)
            uiState = uiState.copy(favorites = currentList)

            try {
                favoritesRepository.toggleFavorite(game)
            } catch (e: Exception) {
                uiState = uiState.copy(
                    favorites = uiState.favorites + game,
                    error = "Error al eliminar el favorito."
                )
            }
        }
    }
}