package com.example.rentcare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun SignUpAs(
    navController: NavController
) {
    Column(
        modifier = Modifier.background(color=Color.White)
            .fillMaxSize(),

        )
    {

        Text(
            text = "RentCare",
            color = Color.Black,
            fontSize = 50.sp,
            // fontWeight = FontWeight.Bold,
            // textAlign = TextAlign.Center
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Sign Up",
            color = Color.Blue,
            fontSize = 80.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 50.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
        )
        Text(
            text = "As",
            color = Color.Blue,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            // textAlign = TextAlign.Center
            modifier = Modifier.padding(
                start = 140.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 8.dp
            )
        )
        Spacer(modifier = Modifier.height(35.dp))
        Column(
            Modifier.padding(start=90.dp)
        ) {
            RoundedButton(
                modifier = Modifier,
                text = "Renter",
                buttonColor = Color.Blue,
                textColor = Color.White,
                height = 48.dp,
                width = 200.dp,
                onClick = {
                    navController.navigate(Screen.SignUpScreen.route){
                        popUpTo(Screen.SignUpScreen.route){
                            inclusive = true
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(25.dp))
            RoundedButton(
                modifier = Modifier.padding(top = 40.dp),
                text = "Flat Owner",
                buttonColor = Color.Black,
                textColor = Color.White,
                height = 48.dp,
                width = 200.dp,
                onClick = {
                    navController.navigate(Screen.SignUpOwner.route){
                        popUpTo(Screen.SignUpOwner.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun RoundedButton(
    modifier: Modifier,
    text: String,
    buttonColor: Color,
    textColor: Color,
    height: Dp,
    width: Dp,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(height)
            .width(width)
            .clip(RoundedCornerShape(percent = 50))
            .background(color = buttonColor)
        ,

        contentPadding = PaddingValues(),

        ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = textColor,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        )
    }
}