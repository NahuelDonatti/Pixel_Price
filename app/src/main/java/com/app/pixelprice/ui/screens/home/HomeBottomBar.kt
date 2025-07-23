package com.app.pixelprice.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.pixelprice.R
import com.app.pixelprice.ui.screens.Screens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeBottomBar(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
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
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            isSelected = navController.currentDestination?.route == Screens.Home.route
        )
        // FAVORITOS
        BottomBarItem(
            icon = Icons.Default.Star,
            label = "Deseados",
            onClick = {  navController.navigate(Screens.Favorites.route) },
            isSelected = false
        )

        // CERRAR SESIÓN
        BottomBarItem(
            icon = Icons.Default.ArrowForward,
            label = "Salir",
            onClick = {
                FirebaseAuth.getInstance().signOut()

                val googleSignInClient = GoogleSignIn.getClient(context,
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                    requestIdToken(context.getString(R.string.default_web_client_id)).requestEmail().build())

                googleSignInClient.signOut().addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Log.d("AUTH", "Sesión de google cerrada correctamente")
                    } else {
                        Log.e("AUTH", "Error al cerrar sesión de google: ${task.exception?.message}")
                    }
                }

                navController.navigate(Screens.Login.route) {
                    popUpTo(0) { inclusive = true }
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
