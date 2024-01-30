package com.example.rentcare

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.rentcare.R

sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : Int,
    val icon_focused : Int
){
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home,
        icon_focused = R.drawable.ic_home_focused
    )
    object Notifications : BottomBarScreen(
        route = "notifications",
        title = "Notifications",
        icon = R.drawable.ic_notifications,
        icon_focused = R.drawable.ic_notifications_focused
    )
    object Profile : BottomBarScreen(
            route = "profile",
            title = "Profile",
            icon = R.drawable.ic_image,
            icon_focused = R.drawable.ic_profile_focused
    )
}
