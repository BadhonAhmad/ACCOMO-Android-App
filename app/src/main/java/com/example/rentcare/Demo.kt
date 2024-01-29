package com.example.rentcare

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rentcare.HomePage
import com.example.rentcare.NavigationItem
import com.example.rentcare.Notifications
import com.example.rentcare.Profile
import com.example.rentcare.Screen

data class BottomNavigationItems(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@Composable
fun NavigationController(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.HomePage.route){
        composable(NavigationItem.HomePage.route){
            HomePage(navController)
        }
        composable(NavigationItem.Notifications.route){
            Notifications(navController)
        }
        composable(NavigationItem.Profile.route){
            Profile(navController)
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar() {
            val items = listOf(
                BottomNavigationItems(
                    title = "Home",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    hasNews = false,
                ),
                BottomNavigationItems(
                    title = "Profile",
                    selectedIcon = Icons.Filled.Person,
                    unselectedIcon = Icons.Outlined.Person,
                    hasNews = false,
                    badgeCount = 10
                ),
                BottomNavigationItems(
                    title = "Notifications",
                    selectedIcon = Icons.Filled.Notifications,
                    unselectedIcon = Icons.Outlined.Notifications,
                    hasNews = true,
                ),
            )

            val navController = rememberNavController()

            Scaffold(
                bottomBar = { BottomBar(items, navController) },
//                modifier = Modifier.padding(4.dp)
            ) {
                NavHost(navController = navController, startDestination = Screen.HomePage.route) {
                    composable("Notifications") { Screen.Notifications.route }
                    composable("Profile") { Screen.Profile.route }
//                composable("Class0"){ Class0.View0()}
                }
            }
        }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(items:List<BottomNavigationItems>, navController: NavController)
{
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    NavigationBar(
        containerColor  = Color.Black,
        modifier = Modifier.height(100.dp)

    ) {
        items.forEachIndexed {
                index, item ->

            NavigationBarItem(
//                            colors = NavigationBarItemDefaults.colors(Color(0xFFBB6748)),
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    if(index==0){
                        navController.navigate("RestaurantClass")
                    }
                    else if(index==1){
                        navController.navigate("CartClass")
                    }
                    else{
                        navController.navigate("Login")
                    }
                    // navController.navigate(item.title)
                },
                label = {
                    Text(text = item.title,
//                                    fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                },
//                            alwaysShowLabel = false,
                icon = {
                    BadgedBox(
                        badge = {
                            if(item.badgeCount != null) {
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            }


                            else if(item.hasNews) {
                                Badge()
                            }   //apatoto no needed
                        }
                    ) {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = "",
                            tint = if(index == selectedItemIndex) Color.Blue else Color(0xFFBB6748)
                        )
                    }
                }
            )
        }
    }
}