package com.example.rentcare


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//import androidx.wear.compose.material.ContentAlpha

@Composable
fun ClickableIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
//    tint: Color = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        content = {
            Icon(
                imageVector = icon,
                contentDescription = null
                //tint = tint
            )
        }
    )
}


@Composable
fun FindAUnit(navController: NavController) {
    val ffname = remember { mutableStateOf("") }
    val ffnumber = remember { mutableStateOf("") }
    val ffunit = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        Row(

        )
        {
            Text(
                text = "Home Page",
                color = Color.Blue,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                )
            )
            Spacer(modifier = Modifier.width(200.dp))
            ClickableIcon(
                icon = Icons.Default.Notifications,
                onClick = {
                    // Handle icon click event
                },
                modifier = Modifier.size(50.dp),
                //tint = Color.Red
            )

            ClickableIcon(
                icon = Icons.Default.Person,
                onClick = {
                    // Handle icon click event
                },
                modifier = Modifier.size(50.dp),
                //tint = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.buildingheight),
                contentDescription = null,
                modifier = Modifier

            )
            Spacer(modifier = Modifier.width(100.dp))
            RoundedButton(
                modifier = Modifier.padding(top = 40.dp,end = 16.dp),
                text = "Create Apartment",
                buttonColor = Color.Black,
                textColor = Color.White,
                height = 48.dp,
                width = 150.dp,
                onClick = {
                    navController.navigate(Screen.CreateFlat.route){
                        popUpTo(Screen.CreateFlat.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
        Text(
            text = "Find a Unit :",
            color = Color.Blue,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 8.dp
            )
        )
        CustomTextField(
            labelText = "Flat's name",
            icon = Icons.Default.Home,
            height = 80.dp,
            width = 200.dp,
            value = ffname.value,
            onValueChange = { newText -> ffname.value = newText }
        )
        CustomTextField(
            labelText = "Floor Number",
            icon = Icons.Default.List,
            height = 80.dp,
            width = 200.dp,
            value = ffnumber.value,
            onValueChange = { newText -> ffnumber.value = newText }
        )
        CustomTextField(
            labelText = "Unit Number",
            icon = Icons.Default.List,
            height = 80.dp,
            width = 200.dp,
            value = ffunit.value,
            onValueChange = { newText -> ffunit.value = newText }
        )
        Column(
            Modifier.padding(start=90.dp , top = 40.dp)
        ) {
            RoundedButton(
                modifier = Modifier.padding(top = 40.dp),
                text = "Find Unit",
                buttonColor = Color.Black,
                textColor = Color.White,
                height = 48.dp,
                width = 200.dp,
                onClick = {
                    navController.navigate(Screen.RentersUnitDetails.route){
                        popUpTo(Screen.RentersUnitDetails.route){
                            inclusive = true
                        }
                    }
                }
            )
        }

    }

}