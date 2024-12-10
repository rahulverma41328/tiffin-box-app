package com.example.tifinbox.dashboard.bottomnav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tifinbox.dashboard.screen.ScreenCart
import com.example.tifinbox.dashboard.screen.ScreenHome
import com.example.tifinbox.dashboard.screen.ScreenProfile
import com.example.tifinbox.dashboard.screen.ScreenSearch

@Composable
fun BottomNavGraph(
    nvaController: NavHostController){

    NavHost(navController = nvaController, startDestination = BottomBarScreen.Home.routes){
        composable(route = BottomBarScreen.Home.routes) {
            ScreenHome()
        }
        composable(route = BottomBarScreen.Cart.routes) {
            ScreenCart()
        }
        composable(route = BottomBarScreen.Search.routes) {
            ScreenSearch()
        }
        composable(route = BottomBarScreen.Profile.routes) {
            ScreenProfile()
        }
    }
}