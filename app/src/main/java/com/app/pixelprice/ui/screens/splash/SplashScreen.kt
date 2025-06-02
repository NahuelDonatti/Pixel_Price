package com.app.pixelprice.ui.screens.splash

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import coil.compose.AsyncImage
import com.app.pixelprice.ui.screens.Screens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    DisposableEffect(key1 = Unit) {
        Log.d("SplashScreenLifecycle", "SplashScreen Composable ENTERED composition.")
        onDispose {
            Log.d("SplashScreenLifecycle", "SplashScreen Composable LEFT composition.")
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Puedes poner aquí tu logo o un CircularProgressIndicator
        // Image(painter = painterResource(id = R.drawable.your_logo), contentDescription = "Logo", modifier = Modifier.size(120.dp))
        // CircularProgressIndicator()
        Text("Cargando...") // Un simple texto de carga por ahora
    }

    LaunchedEffect(key1 = Unit) {
        // Añade un pequeño retraso para que la SplashScreen sea visible (opcional)
        delay(1500) // Espera 1.5 segundos

        val currentUser = FirebaseAuth.getInstance().currentUser
        Log.d("SplashScreen", "Verificando usuario: UID = ${currentUser?.uid}")

        if (currentUser != null) {
            // Usuario ya autenticado, ir a la pantalla principal
            navController.navigate(Screens.Home.route) {
                // Limpia la SplashScreen del back stack
                popUpTo(Screens.Splash.route) { inclusive = true }
            }
            Log.d("SplashScreen", "Usuario existente, navegando a Home.")
        } else {
            // No hay usuario autenticado, ir a la pantalla de login
            navController.navigate(Screens.Login.route) {
                // Limpia la SplashScreen del back stack
                popUpTo(Screens.Splash.route) { inclusive = true }
            }
            Log.d("SplashScreen", "No hay usuario, navegando a Login.")
        }
    }
}