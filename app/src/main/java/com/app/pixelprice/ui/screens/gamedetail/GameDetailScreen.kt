package com.app.pixelprice.ui.screens.gamedetail

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.pixelprice.ui.screens.commons.GameDetailsContent



@Composable
fun GameDetailScreen(
    dealID: String,
    modifier: Modifier = Modifier,
    vm: GameDetailScreenViewModel = viewModel()
) {
    LaunchedEffect(dealID) {
        Log.d("GameDetailScreen", "LaunchedEffect activado para dealID: $dealID")
        if (dealID.isNotBlank() && dealID != "0") {
            vm.setDealID(dealID)
        }
    }

    val uiState = vm.uiState

    Box(modifier = modifier.fillMaxSize()) {
        if (vm.uiState.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        GameDetailsContent(
            gameDetails = uiState.gameDetail,
            stores = uiState.store,
            modifier = Modifier.fillMaxSize()

        )
    }

}
