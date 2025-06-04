package com.app.pixelprice.ui.screens.splash

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
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
        Text("Cargando...")
    }

    LaunchedEffect(key1 = Unit) {
        delay(2500)

        val currentUser = FirebaseAuth.getInstance().currentUser
        Log.d("SplashScreen", "Verificando usuario: UID = ${currentUser?.uid}")

        if (currentUser != null) {
            navController.navigate(Screens.Home.route) {
                popUpTo(Screens.Splash.route) { inclusive = true }
            }
            Log.d("SplashScreen", "Usuario existente, navegando a Home.")
        } else {
            navController.navigate(Screens.Login.route) {
                popUpTo(Screens.Splash.route) { inclusive = true }
            }
            Log.d("SplashScreen", "No hay usuario, navegando a Login.")
        }
    }
}