package com.example.rentcare

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentcare.Components.CButton
import com.example.rentcare.MainActivity.DataManager.rentedFlats
import com.example.rentcare.ui.theme.Indigo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ConfirmUnit(navController: NavController) {
    val toastContext = LocalContext.current
    val notification = rememberSaveable{ mutableStateOf("") }
    var email by remember { mutableStateOf(MainActivity.renterInfo?.email) }

    val BASE_URL = "http://192.168.43.186:5001/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }// var femail = MainActivity.ownerInfo?.email ?: ""

    var flatname = MainActivity.unitDetails?.flatname?:""
    // var password by remember { mutableStateOf("") }
    //var con_password by remember { mutableStateOf("") }
    var rent = MainActivity.unitDetails?.rent?:""
    var gas = MainActivity.unitDetails?.gas?:""
    if(notification.value.isNotEmpty()){
        Toast.makeText(LocalContext.current,notification.value, Toast.LENGTH_LONG).show()
        notification.value = ""
    }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            //here add the back arrow
            Icon(
                imageVector = Icons.Default.ArrowBack, // Use the back arrow icon
                contentDescription = null, // Content description can be null for decorative icons
                modifier = Modifier
                    .size(24.dp) // Set the size of the icon
                    .clickable {
                        // Handle back arrow click action (e.g., navigate back)
                        navController.navigate(Screen.EnterUnit.route) {
                            popUpTo(Screen.EnterUnit.route) {
                                inclusive = true
                            }
                        }
                    }
            )
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "   ${MainActivity.unitDetails ?. flatname} ",
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp)),
                    //.background(color = Indigo, shape = RoundedCornerShape(8.dp)),
                    style = TextStyle(
                        fontSize = 25.sp, // Set the font size
                        fontWeight = FontWeight.Bold,
                        color = Color.Black // Set the text color
                    )
                )
            }

        }
        Row(
        )
        {
            Text(
                text = "Owner Info",
                color = Color.Blue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                )
            )
        }
        Text(
            text = "Name : ${MainActivity.unitDetails?.name}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
//                .background(color = SkyBlue,
//                    shape = RoundedCornerShape(8.dp)) // Set the background color of the box
                .border(
                    2.dp,
                    Color.White,
                    shape = RoundedCornerShape(8.dp)
                ) // Set the border of the box
                .padding(8.dp), // Add padding inside the box
            style = TextStyle(
                fontSize = 20.sp, // Set the font size
                fontWeight = FontWeight.Bold,
                color = Color.Black // Set the text color
            )
        )
        Text(
            text = "Email : ${MainActivity.unitDetails?.email}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
//                .background(
//                    color = SkyBlue,
//                    shape = RoundedCornerShape(8.dp) // Set the rounded corners
//                ) // Set the background color of the box
                .border(
                    2.dp,
                    Color.White,
                    shape = RoundedCornerShape(8.dp)
                ) // Set the border of the box
                .padding(8.dp), // Add padding inside the box
            style = TextStyle(
                fontSize = 20.sp, // Set the font size
                fontWeight = FontWeight.Bold,
                color = Color.Black // Set the text color
            )
        )
        Text(
            text = "Rent bill: ${MainActivity.unitDetails?.rent}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 10.dp)
//                .background(color= SkyBlue,
//                    shape = RoundedCornerShape(8.dp)) // Set the background color of the box
//                .border(
//                    2.dp,
//                    Col
//                    shape = RoundedCornerShape(8.dp)
//                ) // Set the border of the box
                .padding(8.dp), // Add padding inside the box
            style = TextStyle(
                fontSize = 20.sp, // Set the font size
                fontWeight = FontWeight.Bold,
                color = Color.Black // Set the text color
            )
        )
        Text(
            text = "Gas and others : ${MainActivity.unitDetails?.gas}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                .padding(8.dp), // Add padding inside the box
            style = TextStyle(
                fontSize = 20.sp, // Set the font size
                fontWeight = FontWeight.Bold,
                color = Color.Black // Set the text color
            )
        )

        Spacer(modifier = Modifier.height(20.dp))
        CButton(text = "Confirm", onClick = {

            val rentedFlats = RentedFlats(
                tenant = MainActivity.renterInfo?.name?:"",
                email =MainActivity.renterInfo?.email?:"",
                mobile =MainActivity.renterInfo?.mobile?:"",
                nid = MainActivity.renterInfo?.nid?:"",
                flatname = flatname,
                rent = rent as Int,
                gas = gas as Int
            )
            // Make the PUT request
            apiService.InputRentedFlats(rentedFlats).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {

                        if (email?.isNotEmpty() == true) {
                            // Use the existing apiService instance
                            val getRentedListCall: Call<List<RentedFlats>> =
                                apiService.GetRentedList(email ?: "")


                            getRentedListCall.enqueue(object : Callback<List<RentedFlats>> {
                                override fun onResponse(
                                    call: Call<List< RentedFlats >>,
                                    response: Response<List <RentedFlats>>
                                ) {
                                    if (response.isSuccessful) {
                                        val resultList: List<RentedFlats>? = response.body()
                                        if (resultList != null && resultList.isNotEmpty()) {
                                            MainActivity.rentedList = resultList

//                                            navController.navigate(Screen.ConfirmUnit.route) {
//                                                popUpTo(Screen.ConfirmUnit.route) {
//                                                    inclusive = true
//                                                }
//                                            }
                                        }
                                        navController.navigate(Screen.HomePage.route){
                                            popUpTo(Screen.HomePage.route){
                                                inclusive = true
                                            }
                                        }

                                    } else {
                                        val msg = response.message()
                                        // Handle the case where the response body is empty or null
                                        Log.d("badhonvaiQuery",msg)
                                    }
                                }

                                override fun onFailure(
                                    call: Call<List<RentedFlats>>,
                                    t: Throwable
                                ) {
                                    Toast.makeText(
                                        toastContext,
                                        "Network failure. Error: ${t.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
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
    }
}
private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}