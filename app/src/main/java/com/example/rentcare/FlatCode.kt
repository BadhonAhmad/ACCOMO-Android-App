package com.example.rentcare

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentcare.Components.CButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun FlatCode(navController: NavController) {


                Column(
                    modifier = Modifier
                        .fillMaxHeight() // Fill the available height in the Column
                ) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, // Use the back arrow icon
                            contentDescription = null, // Content description can be null for decorative icons
                            modifier = Modifier
                                .size(30.dp) // Set the size of the icon
                                .clickable {
                                    // Handle back arrow click action (e.g., navigate back)
                                    navController.navigate(Screen.FindUnit.route) {
                                        popUpTo(Screen.FindUnit.route) {
                                            inclusive = true
                                        }
                                    }
                                }
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(30.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 100.dp),
                            text = "Code for ${MainActivity.flatCode?.flatname} unit is :",
                            fontSize = 28.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Spacer(modifier = Modifier.height(10.dp)) // Added space between "Submit" and "Cancel"
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = MainActivity.flatCode?.code ?: "", // Handle nullability by providing a default value
                                fontSize = 50.sp,
                                fontWeight = FontWeight.W500,
                                fontStyle = FontStyle.Italic
                            )
                        }
                    }
                }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun prev(){
    FlatCode(navController = rememberNavController())
}