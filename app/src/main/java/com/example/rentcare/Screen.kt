package com.example.rentcare

sealed class Screen(val route : String){
    object Welcome : Screen(route = "Welcome_screen")
    object Login : Screen(route = "Login_screen")
    object Profile : Screen(route= "Profile_screen")
    object SignUpScreen : Screen(route = "Signup_screen")
    object HomePage : Screen(route = "Home_page")
}
