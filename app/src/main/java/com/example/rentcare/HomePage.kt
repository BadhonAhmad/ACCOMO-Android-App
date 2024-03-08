package com.example.rentcare

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.util.*
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    val rentedUnits = remember { mutableStateListOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Home") },
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.Profile.route){
                                    popUpTo(Screen.Profile.route){
                                        inclusive = true
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Person, contentDescription = "Profile")
                        }
                        IconButton(
                            onClick = {
                                // Handle notifications button click
                                navController.navigate(Screen.Notifications.route){
                                    popUpTo(Screen.Notifications.route){
                                        inclusive = true
                                    }
                                }

                            }
                        ) {
                            Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                        }
                    }
                }
            )
        },
        content = {
            // Add Unit button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp, start = 16.dp, end = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    FloatingActionButton(
                        onClick = {
                            //EnterUnit
                            navController.navigate(Screen.EnterUnit.route){
                                popUpTo(Screen.EnterUnit.route){
                                    inclusive = true
                                }
                            }
                        },
                        content = {
                            Text(
                                text = "Add Unit",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    )
                }
            }


            LazyColumn {
                items(rentedUnits) { unit ->
                    Text(
                        text = "Rented Unit: $unit",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable {
                                navController.navigate(Screen.YourUnit.route){
                                    popUpTo(Screen.YourUnit.route){
                                        inclusive = true
                                    }
                                }
                            }
                    )
                }
            }
        }
    )
}


