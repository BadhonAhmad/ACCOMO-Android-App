package com.example.rentcare

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route : String, val label : String, val icon : ImageVector){
    object HomePage:NavigationItem(
        route = "home",
        label = "Home",
        icon = Icons.Default.Home
    )
    object Profile:NavigationItem(
        route = "profile",
        label = "Profile",
        icon = Icons.Default.Person
    )
    object Notifications:NavigationItem(
        route = "notifications",
        label = "Notifications",
        icon = Icons.Default.Notifications
    )
    
}
