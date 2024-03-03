package com.example.rentcare

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentcare.ui.theme.SkyBlue
import java.util.*
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    val rentedUnits = remember { mutableStateListOf("Nishorgo") }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = "Home",
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                )
            }

            Box(
                modifier = Modifier
                    .weight(0.1f), // Adjust weight as needed
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(Screen.Profile.route) {
                            popUpTo(Screen.Profile.route) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Icon(Icons.Filled.Person, contentDescription = "Profile")
                }
            }

            Box(
                modifier = Modifier
                    .weight(0.1f), // Adjust weight as needed
                contentAlignment = Alignment.TopEnd
            ) {
                IconButton(
                    onClick = {
                        // Handle notifications button click
                        navController.navigate(Screen.Notifications.route) {
                            popUpTo(Screen.Notifications.route) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(
                        color = Color.Green, // Set the background color
                        shape = RoundedCornerShape(8.dp) // Set the rounded corners
                    )
                    .padding(16.dp) // Add padding inside the box
            ) {
                Text(
                    text = "Rented Units",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, // Set the font size
                    color = Color.Black // Set the text color
                )
            }
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
            ) {
                FloatingActionButton(
                    onClick = {
                        //EnterUnit
                        navController.navigate(Screen.EnterUnit.route) {
                            popUpTo(Screen.EnterUnit.route) {
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
        Box(
            modifier = Modifier
                .clickable { /* Handle click action */ }
                .padding(16.dp)
                .background(
                    color = SkyBlue, // Set the background color
                    shape = RoundedCornerShape(8.dp) // Set rounded corners
                )
                .padding(16.dp) // Add padding inside the box
        ) {
            Text(
                text = "1. NISHORGO-3A",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp, // Set the font size
                color = Color.Black // Set the text color
            )
        }
    }
}



