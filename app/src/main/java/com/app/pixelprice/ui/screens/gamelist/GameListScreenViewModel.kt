package com.app.pixelprice.ui.screens.gamelist

import android.util.Log
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
import java.io.IOException


class GameListScreenViewModel(
    private val gameRepository: IGameRepository = GameRepository(),
    private val storeRepository: IStoreRepository = StoreRepository()) : ViewModel(){

    var uiState by mutableStateOf(GameListScreenState())
        private set

    private var fetchJob: Job? = null
    private var storesJob: Job? = null

    init {
        fetchStores()
    }

    private fun fetchStores(){
        storesJob?.cancel()
        storesJob = viewModelScope.launch {
            try {
                val stores = storeRepository.fetchStores()
                val map = stores.associateBy {it.storeID}
                uiState = uiState.copy(storesMap = map)
                Log.d ("GameListViewModel", "Tiendas cargadas: ${stores.size}")
            }
            catch (e: IOException){
                Log.e("GameListViewModel", "Error cargando tiendas: ${e.message}")
            }
        }
    }

    fun handleInitialQuery(initialQuery: String){
        if (initialQuery.isNotEmpty() && uiState.searchQuery != initialQuery) {
            uiState = uiState.copy(searchQuery = initialQuery)
            fetchGames()
        } else if (initialQuery.isEmpty() && uiState.gameList.isEmpty() && uiState.searchQuery.isEmpty()){
            fetchGames()
        }
    }



    fun fetchGames(){
        fetchJob?.cancel()
        uiState = uiState.copy(isLoading = true, errorMessage = null)
        fetchJob = viewModelScope.launch {
            try {
                val games = gameRepository.fetchGames(uiState.searchQuery).sortedBy { it.gameName }
                uiState = uiState.copy(gameList = games, isLoading = false)

            }
            catch (e: IOException){
                Log.e("PixelPrice", "Error recuperando la lista de juegos")
            }
        }
    }

    fun searchChange(search: String){
       uiState = uiState.copy(searchQuery = search, gameList = uiState.gameList)
    }

    fun searchSubmited(search: String){
        uiState = uiState.copy(searchQuery = search)
        fetchGames()
    }
}


