package com.app.pixelprice.ui.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.app.pixelprice.ui.screens.Screens
import com.app.pixelprice.ui.screens.commons.SearchGameBar



@Composable
fun HomeScreen(navController: NavHostController) {

    val context = LocalContext.current
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchGameBar(
                query = searchQuery.value,
                onQueryChange = { searchQuery.value = it },
                onSearch = { query ->
                    navController.navigate(Screens.GameList.route + "?query=${query}") {
                    }
                },
                onClose = { searchQuery.value = "" },
                onNotificationClick = {
                    Toast.makeText(context, "Notificaciones de Home!", Toast.LENGTH_SHORT).show()
                },
                placeholderText = "Buscar juegos"
            )
        },
        bottomBar = {
            HomeBottomBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            HomeCarouselSection(modifier = Modifier.fillMaxWidth().height(300.dp).padding(horizontal = 16.dp, vertical = 36.dp))

            Spacer(modifier = Modifier.height(12.dp))

            // BOTONES PRINCIPALES
            HomeCategoriesSection(
                onCatalogClick = {
                    navController.navigate(Screens.GameList.route)
                },
                onWishlistClick = {
                    navController.navigate(Screens.Favorites.route)
                },
                onOffersClick = { /* TODO */ },
                onSettingsClick = { /* TODO */ }
            )
        }
    }
}
