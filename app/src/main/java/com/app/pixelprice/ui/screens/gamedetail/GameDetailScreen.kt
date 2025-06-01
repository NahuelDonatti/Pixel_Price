package com.app.pixelprice.ui.screens.gamedetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.pixelprice.ui.screens.commons.GameDetailsContent



@Composable
fun GameDetailScreen(
    gameID: String,
    modifier: Modifier = Modifier,
    vm: GameDetailScreenViewModel = viewModel()
) {
    vm.setGameID(gameID)

    if (vm.uiState.gameID == "0"){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()}

   }
    else {
        GameDetailsContent(vm.uiState.gameDetail)
    }
}