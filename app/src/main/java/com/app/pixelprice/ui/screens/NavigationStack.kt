package com.app.pixelprice.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.app.pixelprice.ui.screens.favorites.FavoritesScreen
import com.app.pixelprice.ui.screens.gamedetail.GameDetailScreen
import com.app.pixelprice.ui.screens.gamelist.GameListScreen
import com.app.pixelprice.ui.screens.home.HomeScreen
import com.app.pixelprice.ui.screens.login.LoginScreen
import com.app.pixelprice.ui.screens.settings.SettingsScreen
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

        composable(route = Screens.GameList.route, arguments = listOf(navArgument("query"){
            type = NavType.StringType
            defaultValue = ""
            nullable = true
        })
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query")
            GameListScreen(navController = navController,
                initialQuery = query ?: "")
        }
        composable(route = Screens.GameDetail.route + "/{dealID}") { it ->
            var dealID = it.arguments?.getString("dealID")
            GameDetailScreen(dealID ?: "0")
        }

        composable(route = Screens.Favorites.route){
            FavoritesScreen(navController = navController)
        }

        composable(route = Screens.Settings.route){
            SettingsScreen(navController = navController)
        }



    }
}