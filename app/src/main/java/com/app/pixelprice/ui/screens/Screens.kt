package com.app.pixelprice.ui.screens

sealed class Screens (val route: String) {
    object Splash: Screens("splash")
    object Home: Screens("home_screen")
    object GameList: Screens("game_list_screen")
    object GameDetail: Screens("game_detail_screen/{gameID}")
    object Login: Screens("login_screen")
}