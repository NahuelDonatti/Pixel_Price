package com.app.pixelprice.ui.screens.login

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import com.app.pixelprice.R
import com.app.pixelprice.ui.screens.Screens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(
    navController: NavHostController,
    vm: LoginScreenViewModel = viewModel()
) {
    DisposableEffect(key1 = Unit) {
        Log.d("LoginScreenLifecycle", "LoginScreen Composable ENTERED composition.")
        onDispose {
            Log.d("LoginScreenLifecycle", "LoginScreen Composable LEFT composition.")
        }
    }
    val uiState by vm.uiState.collectAsState()
    val context = LocalContext.current // Obtener el contexto

    // El launcher se define aquí, en el ámbito de composición
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("LoginScreenDebug", "Resultado de GoogleSignInLauncher. ResultCode: ${result.resultCode}")
        vm.handleGoogleSignInResult(result.resultCode, result.data)
    }

    // Efecto para navegar cuando el usuario está autenticado
    LaunchedEffect(uiState.isAuthenticated) {
        Log.d("LoginScreenDebug", "LaunchedEffect activado. uiState.isAuthenticated: ${uiState.isAuthenticated}")
        if (uiState.isAuthenticated) {
            val currentUser = FirebaseAuth.getInstance().currentUser
            Log.d("LoginScreenDebug", "Usuario autenticado en LaunchedEffect. UID: ${currentUser?.uid}. Navegando a ${Screens.Home.route}")
            navController.navigate(Screens.Home.route) {
                // Limpia el back stack para que el usuario no pueda volver a la pantalla de login
                popUpTo(Screens.Login.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Inicia sesión en PixelPrice")
            Spacer(modifier = Modifier.height(16.dp))

            if (uiState.isLoading) {
                CircularProgressIndicator()
                Log.d("LoginScreenDebug", "Mostrando indicador de carga.")
            } else {
                Button(
                    onClick = {
                        Log.d("LoginScreenDebug", "Botón 'Iniciar sesión con Google' clickeado.")
                        // Obtener el ID de cliente web de strings.xml
                        val webClientId = context.getString(R.string.default_web_client_id)
                        vm.startGoogleSignIn(context, googleSignInLauncher, webClientId)
                    }
                ) {
                    Text("Iniciar sesión con Google")
                }
            }

            uiState.error?.let { errorMessage ->
                Log.e("LoginScreenDebug", "Mostrando error de UI: $errorMessage")
                Text(errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}