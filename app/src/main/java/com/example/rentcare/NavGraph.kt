package com.example.rentcare

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetUpNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        //Ekhane start e jar route thakbe seta theke shuru hobe tai welcome
        startDestination = Screen.Welcome.route
    ){
        composable(route = Screen.Welcome.route){ Welcome(navController) }
        composable(route = Screen.Login.route){ LoginScreen(navController) }
        composable(route = Screen.Profile.route){ Profile(navController) }
        composable(route = Screen.SignUpScreen.route){ SignUpScreen(navController) }
        composable(route = Screen.HomePage.route){ HomePage(navController) }
    }
}