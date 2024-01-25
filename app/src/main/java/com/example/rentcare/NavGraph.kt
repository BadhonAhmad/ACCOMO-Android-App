package com.example.rentcare

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
   NavHost(navController = navController,
        startDestination = Screen.Welcome.route
       ){
       composable(
           route = Screen.Welcome.route
       ){
           Welcome(navController)
       }
       composable(
           route = Screen.Login.route
       ){
           LoginScreen(navController)
       }
   }

}