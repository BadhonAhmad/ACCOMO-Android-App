package com.example.rentcare


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    width: Dp
) {
    TextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = Modifier.width(width)
    )
}


@Composable
fun BoldTextWithNormalText(firstText: String, secondText: String) {
    Row(modifier = Modifier.padding(start = 10.dp)) {
        Text(
            text = firstText,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = secondText,
            fontSize = 25.sp
        )
    }
}
@Composable
fun BillText(firstText : String, taka : MutableState<String>, secondText : String) {
    Row(modifier = Modifier.padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = firstText,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(10.dp))
        SimpleTextField(
            text = taka.value,
            onTextChanged = { newText ->
                taka.value = newText
            },
            width = 100.dp // Adjust the width as needed
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = secondText,
            fontSize = 25.sp,
            )
    }
}

@Composable
fun RentersUnitDetails(navController: NavController) {
    val rent = remember { mutableStateOf("10000") }
    val gasbill = remember { mutableStateOf("10000") }
    val currentbill = remember { mutableStateOf("10000") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "Renter's Information",
            color = Color.Blue,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,

                )
        )
        Spacer(modifier = Modifier.height(10.dp))
        BoldTextWithNormalText("Renter's Name :", "Nobel Ahmed ")
        Spacer(modifier = Modifier.height(16.dp))
        BoldTextWithNormalText("Mobile Number :", "01719513221")
        Spacer(modifier = Modifier.height(16.dp))
        BoldTextWithNormalText("Gmail :", "Nobel26@gmail.com")
        Spacer(modifier = Modifier.height(16.dp))
        BoldTextWithNormalText("NID Number :", "2020331542")
        Spacer(modifier = Modifier.height(16.dp))
        BillText(firstText = "Rent :", taka = rent, secondText = "Taka")
        Spacer(modifier = Modifier.height(16.dp))
        BillText(firstText = "Gas Bill :", taka = gasbill, secondText = "Taka")
        Spacer(modifier = Modifier.height(16.dp))
        BillText(firstText = "Current Bill :", taka = currentbill, secondText = "Taka")
        Text(
            text = "Payment Info.",
            color = Color.Red,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 10.dp,
                bottom = 8.dp
            )
        )

        BoldTextWithNormalText("Rent :", "Paid")
        BoldTextWithNormalText("Current Bill :", "Paid")
        BoldTextWithNormalText("Gas Bill :", "Paid")
        Column(
            Modifier.padding(start=90.dp , top = 20.dp)
        ) {
            RoundedButton(
                modifier = Modifier.padding(top = 40.dp),
                text = "Mark as paid",
                buttonColor = Color.Black,
                textColor = Color.White,
                height = 48.dp,
                width = 200.dp,
                onClick = { /* Define action on button click */ }
            )
            Spacer(modifier = Modifier.height(25.dp))
            RoundedButton(
                modifier = Modifier.padding(top = 40.dp),
                text = "Delete",
                buttonColor = Color.Black,
                textColor = Color.White,
                height = 48.dp,
                width = 200.dp,
                onClick = { /* Define action on button click */ }
            )
        }
    }
}