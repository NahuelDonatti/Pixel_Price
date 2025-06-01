package com.app.pixelprice.ui.screens.gamedetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.pixelprice.data.GameRepository
import com.app.pixelprice.domain.IGameRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameDetailScreenViewModel(private val gameRepository: IGameRepository = GameRepository()) : ViewModel() {
    var uiState by mutableStateOf(GameDetailScreenState())
        private set

    private var fetchJob: Job? = null

    fun fetchGame(){
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            uiState = uiState.copy(gameID = uiState.gameID, gameDetail = gameRepository.fetchGame(uiState.gameID))
        }
    }


    fun setGameID(gameID: String): Unit{
        uiState = uiState.copy(gameID = gameID, gameDetail = uiState.gameDetail)
        fetchGame()
    }
}