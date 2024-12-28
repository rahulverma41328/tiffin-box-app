package com.example.tifinbox.navGraph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tifinbox.helper.StoreUserData
import com.example.tifinbox.productsDetails.screen.AllProductScreen
import com.example.tifinbox.productsDetails.screen.ScreenSPDetails
import com.example.tifinbox.productsDetails.viewModel.ServiceProviderViewModel
import com.example.tifinbox.routes.ProductDetailRoutes


@Composable
fun SPDetailGraph(innerPadding: PaddingValues,viewModel: ServiceProviderViewModel,dataStore:StoreUserData){

    val navHostController = rememberNavController()
    NavHost(navController = navHostController, startDestination = ProductDetailRoutes.allSP){

        composable(route = ProductDetailRoutes.allSP) {
            AllProductScreen(innerPadding,viewModel,dataStore,navHostController)
        }
        composable(route = "${ ProductDetailRoutes.spDetails}/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            ScreenSPDetails(itemId)
        }
    }
}