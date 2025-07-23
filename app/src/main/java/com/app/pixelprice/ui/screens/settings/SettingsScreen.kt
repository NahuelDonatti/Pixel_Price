package com.app.pixelprice.ui.screens.settings

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SettingsScreen(navController: NavHostController){
    val firebaseAuth = FirebaseAuth.getInstance()
    val currentUser = firebaseAuth.currentUser


    UserProfile(
        userDisplayName = currentUser?.displayName,
        userPhotoUrl = currentUser?.photoUrl?.toString(),
        onLogoutClick = {firebaseAuth.signOut()})
}