package com.app.pixelprice.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


// --- HomeScreen Composable ---

@Composable
fun HomeScreen(navController: NavHostController) { // Recibe el NavController
    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClick = { /* TODO: Implementar búsqueda */ },
                onNotificationsClick = { /* TODO: Implementar notificaciones */ }
            )
        },
        bottomBar = {
            HomeBottomBar(navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // ¡Aplica el padding del Scaffold aquí!
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = "Destacados",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )

            // Sección del Carrusel (por ahora un placeholder)
            HomeCarouselSection(modifier = Modifier.fillMaxWidth().height(200.dp).padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Categorías/Botones
            HomeCategoriesSection(
                onCatalogClick = { /* TODO */ },
                onWishlistClick = { /* TODO */ },
                onOffersClick = { /* TODO */ },
                onSettingsClick = { /* TODO */ }
            )
        }
    }
}
