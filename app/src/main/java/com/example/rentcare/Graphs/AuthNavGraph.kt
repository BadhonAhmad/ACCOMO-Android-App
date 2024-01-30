package com.example.rentcare.Graphs
import Login
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.rentcare.SignUpScreen
import com.example.rentcare.Welcome

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = com.example.rentcare.Graphs.Graph.AUTHENTICATION,
        startDestination = AuthScreen.Welcome.route
    ) {
        composable(route = AuthScreen.Welcome.route) {
            
            Welcome(navController = navController)
//            LoginContent(
//                onClick = {
//                    navController.popBackStack()
//                    navController.navigate(Graph.HOME)
//                },
//                onSignUpClick = {
//                    navController.navigate(AuthScreen.SignUp.route)
//                },
//                onForgotClick = {
//                    navController.navigate(AuthScreen.Forgot.route)
//                }
//            )
        }
        composable(route = AuthScreen.SignUpScreen.route) {
           SignUpScreen(navController = navController)
        }
        composable(route = AuthScreen.Welcome.route) {
            Welcome(navController = navController)
        }
        composable(route = AuthScreen.Login.route) {
            Login(navController = navController)
        }
    }
}


sealed class AuthScreen(val route : String){
    object Welcome : AuthScreen(route = "Welcome_screen")
    object Login : AuthScreen(route = "Login_screen")
    object SignUpScreen : AuthScreen(route = "Signup_screen")


}