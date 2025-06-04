package com.app.pixelprice.ui.screens.gamelist

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.app.pixelprice.ui.screens.Screens
import com.app.pixelprice.ui.screens.commons.GameUIList
import com.app.pixelprice.ui.screens.commons.SearchGameBar

private const val TAG = "GameListDebug"



@Composable
fun GameListScreen(modifier: Modifier = Modifier,
                   vm: GameListScreenViewModel = viewModel(),
                   navController: NavHostController,
                   initialQuery: String = ""){

    val context = LocalContext.current

    LaunchedEffect(key1 = initialQuery) {
        Log.d(TAG, "LaunchedEffect para query inicial: $initialQuery")
        vm.handleInitialQuery(initialQuery)
    }

    Scaffold(
        topBar = {
            SearchGameBar(
                query = vm.uiState.searchQuery,
                onQueryChange = { vm.searchChange(it) },
                onSearch = { vm.searchSubmited(it) },
                onClose = {
                    vm.searchChange("")
                    vm.searchSubmited("")
                },
                onNotificationClick = {
                    Toast.makeText(context, "Notificaciones de GameList!", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Botón de notificaciones de GameList presionado.")
                },
                placeholderText = "Filtrar juegos..."
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            if (vm.uiState.isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            vm.uiState.errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Lista de juegos
            if (!vm.uiState.isLoading && vm.uiState.gameList.isEmpty() && vm.uiState.searchQuery.isEmpty()) {
                Text(
                    text = "No hay juegos disponibles.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else if (!vm.uiState.isLoading && vm.uiState.gameList.isEmpty() && vm.uiState.searchQuery.isNotEmpty()) {
                Text(
                    text = "No se encontraron juegos para '${vm.uiState.searchQuery}'",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            else {
                GameListHeader(modifier = Modifier.padding(top = 8.dp))
                GameUIList(
                    vm.uiState.gameList,
                    storesMap = vm.uiState.storesMap,
                    Modifier.fillMaxSize(),
                    onItemClick = { dealID ->
                        Log.d(TAG, "onItemClick: Se hizo clic en el juego con ID: $dealID")
                        navController.navigate(Screens.GameDetail.route + "/${dealID}")
                        Log.d(TAG, "onItemClick: Navegando a GameDetail Screen")
                    }
                )
            }
        }
    }
}
