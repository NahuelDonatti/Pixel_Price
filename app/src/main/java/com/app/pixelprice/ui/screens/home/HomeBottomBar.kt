package com.app.pixelprice.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward // Icono para "salir"
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.pixelprice.ui.screens.Screens

@Composable
fun HomeBottomBar(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface) // Fondo de la barra inferior
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // HOME
        BottomBarItem(
            icon = Icons.Default.Home,
            label = "Inicio",
            onClick = {
                navController.navigate(Screens.Home.route) {
                    // Evitar múltiples instancias de la Home en el back stack
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            isSelected = navController.currentDestination?.route == Screens.Home.route // Resalta si es la pantalla actual
        )
        // FAVORITOS/ESTRELLA
        BottomBarItem(
            icon = Icons.Default.Star,
            label = "Deseados",
            onClick = { /* TODO: Navegar a la pantalla de deseados */ },
            isSelected = false // Implementar lógica de selección si existe
        )
        // CARRITO/COMPRAS
        BottomBarItem(
            icon = Icons.Default.ShoppingCart,
            label = "Carrito",
            onClick = { /* TODO: Navegar a la pantalla del carrito */ },
            isSelected = false
        )
        // CERRAR SESIÓN / SALIR (usé ArrowForward como ejemplo)
        BottomBarItem(
            icon = Icons.Default.ArrowForward,
            label = "Salir",
            onClick = {
                // Ejemplo de cómo navegar a la pantalla de Login y limpiar el back stack
                navController.navigate(Screens.Login.route) {
                    popUpTo(0) { inclusive = true } // Limpia todo el back stack
                }
            },
            isSelected = false
        )
    }
}

@Composable
fun BottomBarItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = label,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelSmall,
            fontSize = 10.sp
        )
    }
}
