//package com.example.rentcare
//
//import android.annotation.SuppressLint
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.navigation.NavDestination
//import androidx.navigation.NavDestination.Companion.hierarchy
//import androidx.navigation.NavGraph.Companion.findStartDestination
//import com.example.rentcare.SetUpNavGraph
//import com.example.rentcare.ui.theme.Indigo
//
//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun BottomNav(navController: NavHostController) {
//    //val navController = rememberNavController()
//    Scaffold(
//        bottomBar = { BottomBar(navController = navController)}
//    ) {
//        SetUpNavGraph(navController = navController)
//        //HomePage(navController = navController)
//    }
//}
//
//@Composable
//fun BottomBar(navController: NavHostController) {
//    val screens = listOf(
//        BottomBarScreen.Home,
//        BottomBarScreen.Notifications,
//        BottomBarScreen.Profile
//    )
//    val navStackbackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navStackbackEntry?.destination
//    Row(
//        modifier = Modifier
//            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
//            .background(Color.White)
//            .fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ){
//        screens.forEach{
//            screen->
//            AddItem(screen = screen, currentDestination = currentDestination, navController =navController)
//        }
//    }
//}
//
//@Composable
//fun RowScope.AddItem(
//    screen : BottomBarScreen,
//    currentDestination : NavDestination?,
//    navController: NavHostController
//    ) {
//
//    val selected = currentDestination?.hierarchy?.any{it.route == screen.route} == true
//    val background =
//        if(selected) Indigo.copy(alpha = 0.6f) else Color.Transparent
//    val contentColor = if(selected) Color.White else Color.Black
//
//    Box(
//        modifier = Modifier
//            .clip(CircleShape)
//            .background(background)
//            .clickable(
//                onClick = {
//                    navController.navigate(screen.route) {
//                        popUpTo(navController.graph.findStartDestination().id)
//                        launchSingleTop = true
//                    }
//                }
//            )
//    ){
//        Row(
//            modifier = Modifier
//                .padding(start = 10.dp,end = 10.dp,top = 8.dp,bottom = 8.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(4.dp)
//        ){
//            Icon(
//                painter = painterResource(id = if(selected) screen.icon_focused else screen.icon),
//                contentDescription = "icon",
//                tint = contentColor
//            )
//            AnimatedVisibility(visible = selected) {
//                Text(
//                    text = screen.title,
//                    color = contentColor
//                )
//            }
//
//        }
//    }
//}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun BottomNavPreview(){
//    BottomNav()
//}