package com.example.rentcare


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentcare.Components.CButton
import com.example.rentcare.ui.theme.Indigo

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
        .background(Color.White)
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
        Spacer(modifier = Modifier.height(10.dp)) //Adds vertical space of 16 density-independent pixels (dp)
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ){
            Text(
                text = "ACCOMO",
                fontSize = 60.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(25.dp)) //Adds vertical space of 16 density-independent pixels (dp)

        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "We're thrilled to have you join our community for managing your rented residence. Let's simplify the world of renting and leasing together!",
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(50.dp)) //Adds vertical space of 16 density-independent pixels (dp)
        CButton(text = "Get Started!", onClick = {
            navController.navigate(Screen.Otp.route) {
                popUpTo(Screen.Otp.route) {
                    inclusive = true
                }
            }
        })
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WelcomePreview(){
        Welcome(navController = rememberNavController())
}
