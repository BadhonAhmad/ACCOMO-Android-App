package com.example.rentcare



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    labelText: String,
    icon: ImageVector,
    height: Dp,
    width: Dp,
    value: String,
    onValueChange: (String) -> Unit
) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .height(height)
            .fillMaxWidth().padding(
                start=16.dp,
                end=16.dp,
                top=16.dp,
                bottom = 16.dp
            ),
        label = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = labelText)
            }
        }
    )
}

@Composable
fun CreateFlat(navController: NavController) {
    val fadress = remember { mutableStateOf("") }
    val fname = remember { mutableStateOf("") }
    val nfloor = remember { mutableStateOf("") }
    val nunit = remember { mutableStateOf("") }
    Column(
        modifier= Modifier.fillMaxSize()
    ){
        Text(
            text = "Flat's Information :",
            color = Color.Blue,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 100.dp,
                bottom = 8.dp
            )
        )

        CustomTextField(
            labelText = "Flat's Name",
            icon = Icons.Default.Home,
            height = 80.dp,
            width = 200.dp,
            value = fname.value,
            onValueChange = { newText -> fname.value = newText }
        )
        CustomTextField(
            labelText = "Number of floor",
            icon = Icons.Default.List,
            height = 80.dp,
            width = 200.dp,
            value = nfloor.value,
            onValueChange = { newText -> nfloor.value = newText }
        )
        CustomTextField(
            labelText = "Number of Unit (Each floor)",
            icon = Icons.Default.List,
            height = 80.dp,
            width = 200.dp,
            value = nunit.value,
            onValueChange = { newText -> nunit.value = newText }
        )
        Spacer(modifier = Modifier.height(25.dp))
        Column(
            Modifier.padding(start=90.dp)
        ) {
            RoundedButton(
                modifier = Modifier.padding(top = 40.dp),
                text = "Create",
                buttonColor = Color.Black,
                textColor = Color.White,
                height = 48.dp,
                width = 200.dp,
                onClick = {
                    navController.navigate(Screen.FindAUnit.route){
                        popUpTo(Screen.FindAUnit.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}