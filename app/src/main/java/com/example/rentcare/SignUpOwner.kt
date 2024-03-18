package com.example.rentcare

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.rentcare.Components.CButton
import com.example.rentcare.ui.theme.Indigo
import com.example.rentcare.ui.theme.SkyBlue
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpOwner(
    navController: NavController
) {
    val toastContext = LocalContext.current
    val BASE_URL = "http://192.168.43.186:5001/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    var nam = remember { mutableStateOf(TextFieldValue()) }
    var address = remember { mutableStateOf(TextFieldValue()) }
    var mobile = remember { mutableStateOf(TextFieldValue()) }
    var email = remember { mutableStateOf(TextFieldValue()) }
    var bkash = remember { mutableStateOf(TextFieldValue()) }
    // var password by remember { mutableStateOf("") }
    //var con_password by remember { mutableStateOf("") }
    var password = remember { mutableStateOf(TextFieldValue()) }
    Surface(
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 50.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight(500),
                        color = Color.Black
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

        }
        Box(
            modifier = Modifier
                .padding(top = 50.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 50.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                OutlinedTextField(
                    value = nam.value,
                    onValueChange = { nam.value = it },
                    singleLine = true,
                    label = { Text(text = "Enter your name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value =address.value ,
                    onValueChange ={ address.value=it},
                    singleLine = true,
                    label = { Text(text = "Address") },
                    //colors = TextFieldDefaults.colors(),
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value =mobile.value ,
                    onValueChange ={ mobile.value=it},
                    singleLine = true,
                    label = { Text(text = "Mobile") },
                    //colors = TextFieldDefaults.colors(),
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value =email.value ,
                    onValueChange ={ email.value=it},
                    singleLine = true,
                    label = { Text(text = "Email") },
                    //colors = TextFieldDefaults.colors(),
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value =bkash.value ,
                    onValueChange ={ bkash.value=it},
                    singleLine = true,
                    label = { Text(text = "bkash number") },
                    //colors = TextFieldDefaults.colors(),
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                    shape = RoundedCornerShape(15.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value =password.value ,
                    onValueChange ={ password.value=it},
                    singleLine = true,
                    label = { Text(text = "password") },
                    //colors = TextFieldDefaults.colors(),
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                    shape = RoundedCornerShape(15.dp)
                )

                Spacer(modifier = Modifier.height(50.dp))
                CButton(text = "Sign Up", onClick = {
                    // Create a RenterInfo object with the entered data
                    val ownerInfo = OwnerInfo(
                        name = nam.value.text,
                        address = address.value.text,
                        mobile = mobile.value.text,
                        email = email.value.text,
                        bkash = bkash.value.text,
                        password = password.value.text
                    )
                    // Make the PUT request
                    apiService.InputOwnerInfo(ownerInfo).enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                ownerInfo.email = email.value.text
                                ownerInfo.mobile = mobile.value.text
                                ownerInfo.bkash = bkash.value.text
                                ownerInfo.address = address.value.text

                                navController.navigate(Screen.FindUnit.route){
                                    popUpTo(Screen.FindUnit.route){
                                        inclusive = true
                                    }
                                }
                            } else {
                                // Handle error
                                val errorMessage = "Error: ${response.code()}"
                                // Show error message to the user
                                showToast(toastContext,errorMessage)
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            val errorMessage = "Failed to update: ${t.localizedMessage}"
                            // Show error message to the user
                            showToast(toastContext,errorMessage)
                        }
                    })


                },
                    containerColor = Indigo
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.padding(top = 12.dp,bottom = 40.dp)) {
                    Text("Already have an account?",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    )
                    Text("Sign in",
                        style= TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(800),
                            color = Color.Black
                        ),
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.Login.route){
                                popUpTo(Screen.Login.route){
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignUpScre() {
    SignUpOwner(navController = rememberNavController())
}



