package com.app.pixelprice.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.pixelprice.ui.screens.gamelist.GameListScreen
import com.app.pixelprice.ui.screens.splash.SplashScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ){
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screens.GameList.route) {
            GameListScreen()
        }
        composable(route = Screens.GameDetail.route) {
            //GameDetailScreen()
        }
    }
}