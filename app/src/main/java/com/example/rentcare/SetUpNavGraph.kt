package com.example.rentcare

import Login
import androidx.compose.runtime.Composable
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
        composable(route = "Welcome_screen"){ Welcome(navController) }
        composable(route = "Login_screen"){ Login(navController) }
        composable(route = "Signup_screen"){ SignUpScreen(navController) }
        composable(route = "profile"){ Profile(navController) }
        composable(route = "notifications"){ Notifications(navController) }
    }
}