package com.example.rentcare

sealed class Screen(val route : String){
    object Welcome : Screen(route = "Welcome_screen")
    object Login : Screen(route = "Login_screen")

    object SignUpScreen : Screen(route = "Signup_screen")
    object HomePage : Screen(route = "Home_page")
    object Notifications : Screen(route = "Notifications")
    object Profile : Screen(route = "Profile")




}
