package com.example.shopsphere.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.shopsphere.presentation.screen.detail.ProductDetailScreen
import com.example.shopsphere.presentation.screen.home.HomeScreen

@Composable
fun NavController() {


    val controller = rememberNavController()

    NavHost(
        navController = controller,
        startDestination = Route.HomeScreen
    ) {

        composable<Route.HomeScreen>{

            HomeScreen(controller)
        }


        composable<Route.ProductDetailScreen>{backStack->

            val route = backStack.toRoute<Route.ProductDetailScreen>()

            ProductDetailScreen(controller,route.id)


        }





    }


}