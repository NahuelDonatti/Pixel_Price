package com.app.pixelprice.ui.screens.gamedetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pixelprice.data.GameRepository
import com.app.pixelprice.data.IStoreRepository
import com.app.pixelprice.data.StoreRepository
import com.app.pixelprice.domain.IGameRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import android.util.Log
import com.app.pixelprice.data.FavoriteGame
import com.app.pixelprice.data.FavoritesRepository
import com.app.pixelprice.data.GameDetailsResponse
import com.app.pixelprice.data.IFavoritesRepository
import com.app.pixelprice.data.emptyGame
import com.app.pixelprice.data.gameInfo

class GameDetailScreenViewModel(
    private val gameRepository: IGameRepository = GameRepository(),
    private val storeRepository: IStoreRepository = StoreRepository(),
    private val favoritesRepository: IFavoritesRepository = FavoritesRepository()) : ViewModel()
{

    var uiState by mutableStateOf(GameDetailScreenState())
        private set

    private var fetchJob: Job? = null

    fun fetchGame(dealID: String) {
          fetchJob?.cancel()
        uiState = uiState.copy(dealID = dealID, isLoading = true)
        fetchJob = viewModelScope.launch {
            try {
                val storesMap = storeRepository.getStoresMap()
                val fetchedGameDetail = gameRepository.fetchGame(dealID)

                val isFavorite = favoritesRepository.isFavorite(fetchedGameDetail.gameInfo.gameID)

                val storeID = fetchedGameDetail.gameInfo.storeID
                val gameStore = storesMap[storeID]

                uiState = uiState.copy(
                    gameDetail = fetchedGameDetail,
                    store = gameStore,
                    isLoading = false,
                    isFavorite = isFavorite
                )
            } catch (e: Exception) {
                Log.e("GameDetailVM", "Excepción al cargar detalles para $dealID: ${e.message}", e)
                uiState = uiState.copy(
                    gameDetail = emptyGame(),
                    store = null,
                    isLoading = false,
                    isFavorite = false
                )
            }
        }
    }
    fun onFavoriteToggle(){
        val details = uiState.gameDetail ?: return

        viewModelScope.launch {
            val gameFavorite = FavoriteGame(
                gameID = details.gameInfo.gameID,
                title = details.gameInfo.name,
                thumb = details.gameInfo.thumb,
                steamAppID = details.gameInfo.steamAppID,
                steamRatingText = details.gameInfo.steamRatingText
            )
            try {
                val newFavoriteState = favoritesRepository.toggleFavorite(gameFavorite)
                uiState = uiState.copy(isFavorite = newFavoriteState)
            } catch (e: Exception){
                Log.e("Favorite", "Error al crear favorito: ${e.message}", e)
            }

        }
    }


    fun setDealID(dealID: String): Unit{
        uiState = uiState.copy(dealID = dealID, gameDetail = uiState.gameDetail)
        fetchGame(dealID)
    }
}