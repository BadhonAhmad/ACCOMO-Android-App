package com.example.rentcare

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTextField(
    modifier : Modifier = Modifier,
    label : String,
    trailing : String
) {
    TextField(
        modifier = modifier,
        value = "",
        onValueChange = {},
        label = {
            Text(text = label)
        },
        colors = TextFieldDefaults.textFieldColors(),
        trailingIcon ={
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = trailing
                )
            }
        }
    )
}