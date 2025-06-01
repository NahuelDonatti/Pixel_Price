package com.app.pixelprice.ui.screens.gamelist

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pixelprice.data.GameRepository
import com.app.pixelprice.domain.IGameRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException


class GameListScreenViewModel(
    private val gameRepository: IGameRepository = GameRepository()) : ViewModel() {

    var uiState by mutableStateOf(GameListScreenState())
        private set

    private var fetchJob: Job? = null

    fun fetchGames(){
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                uiState = uiState.copy(gameList = gameRepository.fetchGames(uiState.searchQuery))
            }
            catch (e: IOException){
                Log.e("PixelPrice", "Error recuperando la lista de juegos")
            }
        }
    }

    fun searchChange(search: String){
       uiState = uiState.copy(searchQuery = search, gameList = uiState.gameList)
    }
}


