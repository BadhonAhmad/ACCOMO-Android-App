package com.example.rentcare

sealed class Screen(val route : String){
    object Welcome : Screen(route = "Welcome_screen")
    object Login : Screen(route = "Login_screen")
    object SignUpScreen : Screen(route = "Signup_screen")
    object Profile : Screen(route = "profile")
    object HomePage : Screen(route = "homepage")
    object Notifications : Screen(route = "notifications")
    object YourUnit: Screen(route = "yourunit")
    object EnterUnit: Screen(route = "enterunit")
    object SignUpAs : Screen(route = "signupas")
    object SignUpOwner : Screen(route =  "owner")
    object ProfileOwner : Screen(route =  "profileowner")
    object CreateFlat : Screen(route =  "createflat")
    object FlatProfile : Screen(route = "flatprofile")
    object FindUnit : Screen(route = "findunit")
    object ConfirmUnit : Screen(route = "confrimunit")

}
