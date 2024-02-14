package com.example.rentcare

sealed class Screen(val route : String){
    object Welcome : Screen(route = "Welcome_screen")
    object Login : Screen(route = "Login_screen")
    object SignUpScreen : Screen(route = "Signup_screen")

}
