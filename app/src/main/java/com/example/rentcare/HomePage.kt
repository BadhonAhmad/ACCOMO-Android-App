package com.example.rentcare

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentcare.ui.theme.Indigo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavController) {
    val toastContext = LocalContext.current
    var code by remember { mutableStateOf(TextFieldValue()) }
//    val BASE_URL = "http://192.168.43.186:5001/"
    val BASE_URL = "http://10.213.36.97:5001/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Home",
                            color = Color.Blue, fontSize = 30.sp
                        )
                    },
                    actions = {
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(55.dp)
                                    .background(
                                        color = Color.LightGray, // Change the background color as needed
                                        shape = CircleShape // Apply a circular shape to the background
                                    )
                                    .clickable {
                                        navController.navigate(Screen.Profile.route) {
                                            popUpTo(Screen.Profile.route) {
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
                    }
                )
            },
            content = {
                Box(
                    modifier = Modifier
                        .padding(top = 80.dp, start = 16.dp, end = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {

                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp, end = 16.dp, top = 8.dp)
                                        .background(color= Color.White, shape = RoundedCornerShape(8.dp))
                                        .border(
                                            width = 2.dp,
                                            color = Color.Black,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                ) {
                                    Text(
                                        text = "Rented Units",
                                        modifier = Modifier.padding(8.dp),
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                    )
                                }
                            }
                                itemsIndexed(MainActivity.rentedList!!) { ind, unit ->
                                var name = unit.flatname

                                Text(
                                    text = " ${ind + 1}: $name",
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .border(
                                            width = 2.dp,
                                            color = Color.Black,
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .clickable {
                                            // Log the clicked name
                                            Log.d("ClickEvent", "Clicked name: $name")

                                            // Check if name is not empty and proceed with the network request
                                            if (name.isNotEmpty()) {
                                                val getStudentInfoCall: Call<List<UnitDetails>> =
                                                    apiService.GetSpecificUnit(name)

                                                getStudentInfoCall.enqueue(object :
                                                    Callback<List<UnitDetails>> {
                                                    override fun onResponse(
                                                        call: Call<List<UnitDetails>>,
                                                        response: Response<List<UnitDetails>>
                                                    ) {
                                                        if (response.isSuccessful) {
                                                            val resultList: List<UnitDetails>? =
                                                                response.body()
                                                            if (resultList != null && resultList.isNotEmpty()) {
                                                                MainActivity.unitDetails =
                                                                    resultList.first()
                                                                if (name.isNotEmpty()) {
//                                                                    val BASE_URL = "http://192.168.43.186:5001/"
                                                                    val BASE_URL = "http://10.213.36.97:5001/"

                                                                    val apiService: ApiService by lazy {
                                                                        Retrofit.Builder()
                                                                            .baseUrl(BASE_URL)
                                                                            .addConverterFactory(
                                                                                GsonConverterFactory.create()
                                                                            )
                                                                            .build()
                                                                            .create(ApiService::class.java)
                                                                    }
                                                                    val getStudentInfoCall: Call<List<BillStatus>> =
                                                                        apiService.GetBillStatus(
                                                                            name
                                                                        )

                                                                    getStudentInfoCall.enqueue(
                                                                        object :
                                                                            Callback<List<BillStatus>> {
                                                                            override fun onResponse(
                                                                                call: Call<List<BillStatus>>,
                                                                                response: Response<List<BillStatus>>
                                                                            ) {
                                                                                if (response.isSuccessful) {
                                                                                    val resultList: List<BillStatus>? =
                                                                                        response.body()
                                                                                    if (resultList != null && resultList.isNotEmpty()) {
                                                                                        MainActivity.billStatus =
                                                                                            resultList.first()

                                                                                    } else {
                                                                                        Toast.makeText(
                                                                                            toastContext,
                                                                                            "No bill status found for $name",
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
                                                                    Toast.makeText(
                                                                        toastContext,
                                                                        "Flat name is empty",
                                                                        Toast.LENGTH_SHORT
                                                                    )
                                                                        .show()
                                                                }
                                                                // Navigate to YourUnit screen
                                                                navController.navigate(Screen.YourUnit.route) {
                                                                    popUpTo(Screen.YourUnit.route) {
                                                                        inclusive = true
                                                                    }
                                                                }
                                                            } else {
                                                                Log.e(
                                                                    "ClickEvent",
                                                                    "Empty or null response body"
                                                                )
                                                            }
                                                        } else {
                                                            Log.e(
                                                                "ClickEvent",
                                                                "Unsuccessful response: ${response.code()}"
                                                            )
                                                        }
                                                    }

                                                    override fun onFailure(
                                                        call: Call<List<UnitDetails>>,
                                                        t: Throwable
                                                    ) {
                                                        Log.e(
                                                            "ClickEvent",
                                                            "Network failure. Error: ${t.message}",

                                                        )
                                                        // Show error message to the user
                                                        Toast.makeText(
                                                            toastContext,
                                                            "Network failure. Error: ${t.message}",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                })
                                            } else {
                                                Log.e("ClickEvent", "Empty name")
                                            }
                                        }
                                )
                            }
                        }
                        FloatingActionButton(
                            onClick = {
                                navController.navigate(Screen.EnterUnit.route) {
                                    popUpTo(Screen.EnterUnit.route) {
                                        inclusive = true
                                    }
                                }
                            },
                            content = {
                                Text(
                                    text = "Add Unit",
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePagePrev() {
    HomePage(navController = rememberNavController())
}