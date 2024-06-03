package com.example.rentcare

import android.os.Bundle
import android.util.Log
import android.view.PixelCopy.Request
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rentcare.Data.otpRequest.ApiResponse
import com.example.rentcare.Data.otpRequest.RequestParameters
//import com.example.abartry.RetrofitStuffs.MyApiService
//import com.example.abartry.RetrofitStuffs.ServiceBuilder
//import com.example.abartry.data.ApiResponse
//import com.example.abartry.data.RequestParameters
//import com.example.abartry.ui.theme.AbarTryTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Otp(navController: NavController) {
    var value by rememberSaveable {
        mutableStateOf("");
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {value = it},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = "Phone number"
                )
            },
            label = { Text("Enter OTP") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier
                .fillMaxWidth(9/11f)
                .padding(10.dp)

        )
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                submit()
            }, modifier = Modifier.width(130.dp)
            ) {
                Text(text = "Request")
            }

            Button(onClick = {
                verify(value)
            },modifier = Modifier.width(130.dp)) {
                Text(text = "Verify")
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                subscriptionOn()
            }, modifier = Modifier.width(130.dp)
            ) {
                Text(text = "Subscribe")
            }

            Button(onClick = {
                subscriptionOff()
            },modifier = Modifier.width(130.dp)) {
                Text(text = "Unsubscribe")
            }
        }

        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                verifyStatus()
            }, modifier = Modifier.width(130.dp)
            ) {
                Text(text = "Status")
            }
        }
    }
}

fun submit() {
    val requestParameters = RequestParameters(
        appId = "APP_119016",
        password = "869a9f8f973ac130f4f578e88f004b06",
        mobile = "8801603252292"
    )


    val destinationService = ServiceBuilder.buildService(ApiService::class.java)
    val requestCall = destinationService.requestOtp(requestParameters)

    requestCall.enqueue(object : Callback<ApiResponse> {
        override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
            if (response.isSuccessful) {
                val apiResponse = response.body()
                Log.d("MyActivity", "OTP sent successfully: $apiResponse")
            } else {
                // Handle unsuccessful response
                Log.e("MyActivity", "Failed to send OTP: ${response.errorBody()?.string()}")
            }
        }
        override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
            // Handle failure
            Log.e("MyActivity", "Network error: ${t.message}")
        }
    })
}

