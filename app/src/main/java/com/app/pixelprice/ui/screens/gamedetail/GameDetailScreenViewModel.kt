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
import com.app.pixelprice.data.emptyGame

class GameDetailScreenViewModel(
    private val gameRepository: IGameRepository = GameRepository(),
    private val storeRepository: IStoreRepository = StoreRepository()) : ViewModel() {

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

                val storeID = fetchedGameDetail.gameInfo.storeID
                val gameStore = storesMap[storeID]

                uiState = uiState.copy(
                    gameDetail = fetchedGameDetail,
                    store = gameStore,
                    isLoading = false
                )
            } catch (e: Exception) {
                Log.e("GameDetailVM", "Excepción al cargar detalles para $dealID: ${e.message}", e)
                uiState = uiState.copy(
                    gameDetail = emptyGame(),
                    store = null,
                    isLoading = false
                )
            }
        }
    }


    fun setDealID(dealID: String): Unit{
        uiState = uiState.copy(dealID = dealID, gameDetail = uiState.gameDetail)
        fetchGame(dealID)
    }
}