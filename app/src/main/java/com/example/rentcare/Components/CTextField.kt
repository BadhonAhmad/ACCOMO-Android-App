package com.example.rentcare.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rentcare.ui.theme.DeepIndigo
import com.example.rentcare.ui.theme.Teal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTextField(
    onValueChange : (String) -> Unit ={

    },
    hint : String,
    value : String,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold
                )
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Teal,
            unfocusedIndicatorColor = DeepIndigo
        )

    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CTextFieldPreview(){
    CTextField(hint = "email", value = "hello")
}