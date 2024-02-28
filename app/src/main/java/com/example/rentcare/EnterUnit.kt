package com.example.rentcare

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentcare.Components.CButton
import com.example.rentcare.Components.CTextField

@Composable
fun EnterUnit(navController: NavController) {
    var code: String
    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxHeight() // Fill the available height in the Column
    ) {
        Text(
            modifier = Modifier.padding(top = 100.dp),
            text = "Enter Your Rented Unit",
            fontSize = 28.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        CTextField(
            hint = "Code",
            leadingIcon = Icons.Filled.Lock,
            onValueChange = {
                code = it
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        CButton(text = "Submit", onClick = {
            navController.navigate(Screen.HomePage.route) {
                popUpTo(Screen.HomePage.route) {
                    inclusive = true
                }
            }
        })
        Spacer(modifier = Modifier.height(10.dp)) // Added space between "Submit" and "Cancel"
        Row(
            modifier = Modifier
                .fillMaxWidth(),
                //.padding(start = 60.dp, end = 2.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ClickableText(
                text = AnnotatedString("Cancel"),
                onClick = { offset ->
                    navController.navigate(Screen.HomePage.route) {
                        popUpTo(Screen.HomePage.route) {
                            inclusive = true
                        }
                    }
                    // Handle click action for "Cancel"
                    // For now, you can leave it empty or navigate to another destination
                },
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Set the color you desire
                )
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EnterUnitPreview(){
    val navController = rememberNavController()
    EnterUnit(navController = navController)
}