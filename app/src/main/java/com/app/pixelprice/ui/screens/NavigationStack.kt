package com.app.pixelprice.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.pixelprice.ui.screens.gamedetail.GameDetailScreen
import com.app.pixelprice.ui.screens.gamelist.GameListScreen
import com.app.pixelprice.ui.screens.home.HomeScreen
import com.app.pixelprice.ui.screens.login.LoginScreen
import com.app.pixelprice.ui.screens.splash.SplashScreen

@Composable
fun NavigationStack(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ){
        composable(route = Screens.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screens.Login.route){
            LoginScreen(navController = navController)
        }

        composable(route = Screens.Home.route){
            HomeScreen(navController = navController)
        }

        composable(route = Screens.GameList.route) {
            GameListScreen(navController = navController)
        }
        composable(route = Screens.GameDetail.route + "/{gameID}") { it ->
            var gameID = it.arguments?.getString("gameID")
            GameDetailScreen(gameID ?: "0")
        }


    }
}