package com.example.rentcare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentcare.Components.CButton


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
            text = "ACCOMO",
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
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 80.dp,
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
            Modifier.padding(start=40.dp,end = 40.dp)
        ) {
            CButton(text = "Tenant", onClick = {
                navController.navigate(Screen.SignUpScreen.route){
                    popUpTo(Screen.SignUpScreen.route){
                        inclusive = true
                    }
                }
            })

            Spacer(modifier = Modifier.height(25.dp))
            CButton(text = "Owner", onClick = {
                navController.navigate(Screen.SignUpOwner.route){
                    popUpTo(Screen.SignUpOwner.route){
                        inclusive = true
                    }
                }
            })
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ClickableText(
                text = AnnotatedString("Cancel"),
                onClick = { offset ->
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                    // Handle click action for "Cancel"
                    // For now, you can leave it empty or navigate to another destination
                },
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Set the color you desire
                )
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
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ir() {
    SignUpAs(navController = rememberNavController())
}