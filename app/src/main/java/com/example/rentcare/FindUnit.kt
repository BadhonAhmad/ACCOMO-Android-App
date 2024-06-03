package com.example.rentcare


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentcare.Components.CButton
import com.example.rentcare.ui.theme.Indigo
import com.example.rentcare.ui.theme.SkyBlue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//import androidx.wear.compose.material.ContentAlpha

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindUnit(navController: NavController) {
    val toastContext = LocalContext.current
    var fname by remember { mutableStateOf(TextFieldValue()) }
    var ffname by remember { mutableStateOf(TextFieldValue()) }
    var flatname = MainActivity.rentedFlats?.flatname ?: ""
    var owner by remember { mutableStateOf(TextFieldValue(MainActivity.ownerInfo?.email ?: "")) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row {
            Text(
                text = "Home Page",
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
            Spacer(modifier = Modifier.width(130.dp))
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .background(
                        color = Color.LightGray, // Change the background color as needed
                        shape = CircleShape // Apply a circular shape to the background
                    )
                    .clickable {
                        navController.navigate(Screen.ProfileOwner.route) {
                            popUpTo(Screen.ProfileOwner.route) {
                                inclusive = true
                            }
                        }
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null, // Content description can be null for decorative icons
                    modifier = Modifier.size(50.dp), // Set the size of the icon
                    tint = Color.Black // Change the icon color as needed
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Create your apartment :",
            color = Color.Blue,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp 
            )
        )

        CButton(text = "Create Apartment", onClick = {
                navController.navigate(Screen.CreateFlat.route) {
                    popUpTo(Screen.CreateFlat.route) {
                        inclusive = true
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Find a Unit :",
            color = Color.Blue,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
            )
        )
        OutlinedTextField(
            value =fname ,
            onValueChange ={ fname=it},
            singleLine = true,
            label = { Text(text = "Enter Units Name :(eg. 'Name-1-A'") },
            //colors = TextFieldDefaults.colors(),
            modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(15.dp)
        )

        CButton(
            text = "Find Unit",
            onClick = {
                if (fname.text.isNotEmpty()) {
                   val BASE_URL = "http://192.168.43.186:5001/"
//                   val BASE_URL = "http://10.213.36.97:5001/"

                    val apiService: ApiService by lazy {
                        Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(ApiService::class.java)
                    }

                    val getStudentInfoCall: Call<List<RentedFlats>> =
                        apiService.GetFlatDetails(fname.text)

                    getStudentInfoCall.enqueue(object : Callback<List<RentedFlats>> {
                        override fun onResponse(
                            call: Call<List<RentedFlats>>,
                            response: Response<List<RentedFlats>>
                        ) {
                            if (response.isSuccessful) {
                                val resultList: List<RentedFlats>? = response.body()
                                if (resultList != null && resultList.isNotEmpty()) {
                                    MainActivity.rentedFlats = resultList.first()
                                    if (fname.text.isNotEmpty()){
                                        val BASE_URL = "http://192.168.43.186:5001/"
//                                        val BASE_URL = "http://10.213.36.97:5001/"

                                        val apiService: ApiService by lazy {
                                            Retrofit.Builder()
                                                .baseUrl(BASE_URL)
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build()
                                                .create(ApiService::class.java)
                                        }
                                        val getStudentInfoCall : Call<List<BillStatus>> =
                                            apiService.GetBillStatus(fname.text)

                                        getStudentInfoCall.enqueue(object : Callback<List<BillStatus>> {
                                            override fun onResponse(
                                                call: Call<List<BillStatus>>,
                                                response: Response<List<BillStatus>>
                                            ) {
                                                if (response.isSuccessful) {
                                                    val resultList: List<BillStatus>? = response.body()
                                                    if (resultList != null && resultList.isNotEmpty()) {
                                                        MainActivity.billStatus = resultList.first()

                                                    } else {
                                                        Toast.makeText(
                                                            toastContext,
                                                            "No bill status found for $flatname",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                } else {
                                                    Toast.makeText(
                                                        toastContext,
                                                        "API call failed. Code: ${response.code()}",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }

                                            override fun onFailure(
                                                call: Call<List<BillStatus>>,
                                                t: Throwable
                                            ) {
                                                Toast.makeText(
                                                    toastContext,
                                                    "${t.message}",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        })
                                    } else {
                                        Toast.makeText(toastContext, "Flat name is empty", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                    navController.navigate(Screen.FlatProfile.route) {
                                        popUpTo(Screen.FlatProfile.route) {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    // Handle the case where the response body is empty or null
                                }
                            } else {
                                Toast.makeText(
                                    toastContext,
                                    "API call failed. Code: ${response.code()}",
                                    Toast.LENGTH_SHORT
                                ).show()
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
                } else {
                    Toast.makeText(toastContext, "Please Insert All The Value", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Get Code of unit :",
            color = Color.Blue,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 8.dp
            )
        )
        OutlinedTextField(
            value =ffname ,
            onValueChange ={ ffname=it},
            singleLine = true,
            label = { Text(text = "Enter Units Name :(eg. 'Name-1-A'") },
            //colors = TextFieldDefaults.colors(),
            modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(15.dp)
        )
        CButton(
            text = "Get Code",
            onClick = {
                if (ffname.text.isNotEmpty() ){
                    val BASE_URL = "http://192.168.43.186:5001/"
//                    val BASE_URL = "http://10.213.36.97:5001/"

                    val apiService: ApiService by lazy {
                        Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(ApiService::class.java)
                    }
                    val getStudentInfoCall : Call<List<FlatCode>> =
                        apiService.GetFlatCode(ffname.text)

                    getStudentInfoCall.enqueue(object : Callback<List<FlatCode>> {
                        override fun onResponse(
                            call: Call<List<FlatCode>>,
                            response: Response<List<FlatCode>>
                        ) {
                            if (response.isSuccessful) {
                                val resultList: List<FlatCode> ?= response.body()
                                if (resultList != null && resultList.isNotEmpty()) {
                                    MainActivity.flatCode = resultList.first()
                                    navController.navigate(Screen.FlatCode.route) {
                                        popUpTo(Screen.FlatCode.route) {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    // Handle the case where the response body is empty or null
                                }
                            } else {
                                Toast.makeText(
                                    toastContext,
                                    "API call failed. Code: ${response.code()}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(
                            call: Call<List<FlatCode>>,
                            t: Throwable
                        ) {
                            Toast.makeText(
                                toastContext,
                                "Network failure. Error: ${t.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                } else {
                    Toast.makeText(toastContext, "Please Insert All The Value", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun P() {
    FindUnit(navController = rememberNavController())
}