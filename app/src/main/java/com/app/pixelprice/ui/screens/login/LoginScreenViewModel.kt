package com.app.pixelprice.ui.screens.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// Define un estado de UI para la pantalla de Login
data class LoginUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAuthenticated: Boolean = false // Para indicar si el usuario ha iniciado sesión
)

class LoginScreenViewModel(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    // Necesitarás una forma de obtener el client ID.
    // Esto se puede pasar a través de un factory si no usas Hilt.
    // Para simplificar, asumiremos que se puede acceder al contexto para getString(R.string.default_web_client_id)
    // o que el client ID se pasa de alguna otra forma al ViewModel.

    // Función para iniciar el flujo de Google Sign-In
    fun startGoogleSignIn(
        context: Context, // Necesitas el contexto para GoogleSignIn.getClient
        launcher: ActivityResultLauncher<Intent>,
        webClientId: String // Pasa el default_web_client_id aquí
    ) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        launcher.launch(googleSignInClient.signInIntent)
    }

    // Función para manejar el resultado del ActivityResultLauncher
    fun handleGoogleSignInResult(resultCode: Int, data: Intent?) {
        Log.d("LoginVMDebug", "handleGoogleSignInResult llamado. ResultCode: $resultCode")
        if (resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                Log.d("LoginVMDebug", "GoogleSignIn exitoso. Account ID: ${account.id}")
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)

                viewModelScope.launch {
                    try {
                        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
                        firebaseAuth.signInWithCredential(credential).await() // Usar .await() para coroutines
                        _uiState.value = _uiState.value.copy(isLoading = false, isAuthenticated = true)
                        Log.d("LoginVMDebug", "Inicio de sesión de Firebase exitoso. Usuario: ${firebaseAuth.currentUser?.uid}")
                        Log.d("AUTH", "Inicio de sesión exitoso con Google.")
                    } catch (e: Exception) {
                        _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
                        Log.e("LoginVMDebug", "ERROR: Falló el inicio de sesión con credenciales de Firebase", e)
                        Log.e("AUTH", "Error al iniciar sesión con credenciales de Firebase", e)
                    }
                }
            } catch (e: ApiException) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
                Log.e("LoginVMDebug", "ERROR: Falló Google Sign-In (API Exception)", e)
                Log.e("AUTH", "Error en Google Sign-In (API Exception)", e)
            }
        } else {
            _uiState.value = _uiState.value.copy(isLoading = false, error = "Inicio de sesión cancelado o fallido.")
            Log.w("LoginVMDebug", "Google Sign-In: Resultado NO_OK. Result Code: $resultCode")
            Log.w("AUTH", "Inicio de sesión de Google cancelado o fallido. Result Code: $resultCode")
        }
    }

    // Función para resetear el estado de autenticación (ej. después de navegar)
    fun resetAuthStatus() {
        Log.d("LoginVMDebug", "resetAuthStatus() llamado. Seteando isAuthenticated a false.")
        _uiState.value = _uiState.value.copy(isAuthenticated = false)
    }
}