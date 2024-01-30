package com.example.rentcare

import GradientBackgroundBrush
import android.content.Intent
import android.util.Log
import android.webkit.WebSettings
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentcare.Graphs.AuthScreen

@Composable
fun Welcome(
    navController : NavController
){
    val gradientColorList = listOf(
        Color(0xFF87CEEB),
        Color(0xFF87CEFA)
    )
    Column(modifier = Modifier
        .fillMaxSize()
        .background(
            brush = GradientBackgroundBrush(isVerticalGradient = true,
                colors = gradientColorList)
        )

    ) {
        Box(
            contentAlignment = Alignment.TopCenter
        ){
            Image(
                modifier = Modifier
                    .height(320.dp)
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.welcome1),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ){
            Text(
                text = "Welcome to",
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ){
            Text(
                text = "RENT CARE",
                fontSize = 60.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Text(text="We're thrilled to have you join our community for \n" +
                "managing your rented residence. Let's simplify the world of renting and leasing together!",
            fontSize = 15.sp
        )
        Spacer(modifier = Modifier.height(40.dp)) //Adds vertical space of 16 density-independent pixels (dp)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate(route = AuthScreen.Login.route)
            }){
            Text(text= "Get Started!")
        }
    }
}

//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//fun WelcomePreview(){
//        Welcome(navController = rememberNavController())
//}
