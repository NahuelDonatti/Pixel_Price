package com.app.pixelprice.ui.screens.gamelist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.pixelprice.ui.screens.commons.GameUIList
import com.app.pixelprice.ui.theme.PixelPriceTheme

@Composable
fun GameListScreen(modifier: Modifier = Modifier, vm: GameListScreenViewModel = viewModel()){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Listado de juegos",
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ) {
            TextField(
                value = vm.uiState.searchQuery,
                modifier = Modifier.weight(1f),
                label = { Text("Buscar juegos: ")},
                singleLine = true,
                onValueChange = {vm.searchChange(it)}
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    vm.fetchGames()
                }
            ) {
                Text("Buscar")
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        GameUIList(vm.uiState.gameList, Modifier.fillMaxSize())
    }
}

