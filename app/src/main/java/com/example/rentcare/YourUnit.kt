package com.example.rentcare

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentcare.ui.theme.Indigo
import com.example.rentcare.ui.theme.Orchid
import com.example.rentcare.ui.theme.SkyBlue

@Composable
fun YourUnit(navController: NavController) {


    Column(
        modifier = Modifier.fillMaxSize()
    ) {

            //here add the back arrow
            Icon(
                imageVector = Icons.Default.ArrowBack, // Use the back arrow icon
                contentDescription = null, // Content description can be null for decorative icons
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 10.dp)// Set the size of the icon
                    .clickable {
                        // Handle back arrow click action (e.g., navigate back)
                        navController.navigate(Screen.HomePage.route){
                            popUpTo(Screen.HomePage.route){
                                inclusive = true
                            }
                        }
                    }
            )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "NISHORGO-3A",
                modifier = Modifier
                    .background(Indigo, shape = RoundedCornerShape(8.dp))
                    .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Owner's Info",
                modifier = Modifier
                    .padding(start = 16.dp, top = 8.dp)
                    //.background(Indigo, shape = RoundedCornerShape(8.dp)) // Set the background color of the box with rounded corners
                    .border(
                        2.dp,
                        Color.White,
                        shape = RoundedCornerShape(8.dp)
                    ) // Set the border of the box with rounded corners
                    .padding(8.dp), // Add padding inside the box
                style = TextStyle(
                    fontSize = 20.sp, // Set the font size
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Set the text color
                )
            )
        }

        Text(
            text = "Name : ${MainActivity.renterInfo?.name}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start =16.dp,top = 8.dp , end = 16.dp)
                .background(Color.White) // Set the background color of the box
                .border(2.dp, Color.Black) // Set the border of the box
                .padding(8.dp), // Add padding inside the box
            style = TextStyle(
                fontSize = 20.sp, // Set the font size
                fontWeight = FontWeight.Bold,
                color = Color.Black // Set the text color
            )
        )
        Text(
            text = "Address : ${MainActivity.renterInfo?.address}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start =16.dp,top = 8.dp, end = 16.dp)
                .background(Color.White) // Set the background color of the box
                .border(2.dp, Color.Black) // Set the border of the box
                .padding(8.dp), // Add padding inside the box
            style = TextStyle(
                fontSize = 18.sp, // Set the font size
                fontWeight = FontWeight.Bold,
                color = Color.Black // Set the text color
            )
        )
        Text(
            text = "Email : ${MainActivity.renterInfo?.email}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start =16.dp,top = 8.dp, end = 16.dp)
                .background(Color.White) // Set the background color of the box
                .border(2.dp, Color.Black) // Set the border of the box
                .padding(8.dp), // Add padding inside the box
            style = TextStyle(
                fontSize = 18.sp, // Set the font size
                fontWeight = FontWeight.Bold,
                color = Color.Black // Set the text color
            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Payment Status",
                modifier = Modifier
                    .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                )
            )
        }
        Box(

        ){
            Row(

            ){
                Text(
                    text = "Rent : ${MainActivity.renterInfo?.name}",
                    modifier = Modifier
                        .weight(.5f)
                        .padding(start =16.dp,top = 8.dp)
                        .background(Color.White) // Set the background color of the box
                        .border(2.dp, Color.Black) // Set the border of the box
                        .padding(8.dp), // Add padding inside the box
                    style = TextStyle(
                        fontSize = 20.sp, // Set the font size
                        fontWeight = FontWeight.Bold,
                        color = Color.Black // Set the text color
                    )
                )
                Text(
                    text = "Due",
                    modifier = Modifier
                        .weight(.5f)
                        .background(Color.Yellow)
                        .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                )
            }
        }
        Box(

        ){
            Row(

            ){
                Text(
                    text = "Current Bill: ${MainActivity.renterInfo?.name}",
                    modifier = Modifier
                        .weight(.5f)
                        .padding(start =16.dp,top = 8.dp)
                        .background(Color.White) // Set the background color of the box
                        .border(2.dp, Color.Black) // Set the border of the box
                        .padding(8.dp), // Add padding inside the box
                    style = TextStyle(
                        fontSize = 20.sp, // Set the font size
                        fontWeight = FontWeight.Bold,
                        color = Color.Black // Set the text color
                    )
                )
                Text(
                    text = "Due",
                    modifier = Modifier
                        .weight(.5f)
                        .background(Color.Yellow)
                        .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                )
            }
        }
        Box(

        ){
            Row(

            ){
                Text(
                    text = "Gas Bill : ${MainActivity.renterInfo?.name}",
                    modifier = Modifier
                        .weight(.5f)
                        .padding(start =16.dp,top = 8.dp)
                        .background(Color.White) // Set the background color of the box
                        .border(2.dp, Color.Black) // Set the border of the box
                        .padding(8.dp), // Add padding inside the box
                    style = TextStyle(
                        fontSize = 20.sp, // Set the font size
                        fontWeight = FontWeight.Bold,
                        color = Color.Black // Set the text color
                    )
                )
                Text(
                    text = "Due",
                    modifier = Modifier
                        .weight(.5f)
                        .background(Color.Yellow)
                        .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Have Any Complain?",
                modifier = Modifier
                    .background(SkyBlue, shape = RoundedCornerShape(8.dp))
                    .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "Drop Here",
                modifier = Modifier
                    .background(Orchid, shape = RoundedCornerShape(8.dp))
                    .border(2.dp, Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(8.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }
    }

}