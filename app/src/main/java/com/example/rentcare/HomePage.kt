package com.example.rentcare

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
    val BASE_URL = "http://192.168.43.186:5001/"
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
                title = { Text(text = "Home") },
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.Profile.route){
                                    popUpTo(Screen.Profile.route){
                                        inclusive = true
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Person, contentDescription = "Profile")
                        }
                        IconButton(
                            onClick = {
                                // Handle notifications button click
                                navController.navigate(Screen.Notifications.route){
                                    popUpTo(Screen.Notifications.route){
                                        inclusive = true
                                    }
                                }

                            }
                        ) {
                            Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
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
                        itemsIndexed(MainActivity.rentedList!!) { ind,unit ->
                            var name = unit.flatname

                            Text(
                                text = "${ind+1}: $name",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
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
                                                        val resultList: List<UnitDetails>? = response.body()
                                                        if (resultList != null && resultList.isNotEmpty()) {
                                                            MainActivity.unitDetails = resultList.first()

                                                            // Navigate to YourUnit screen
                                                            navController.navigate(Screen.YourUnit.route) {
                                                                popUpTo(Screen.YourUnit.route) {
                                                                    inclusive = true
                                                                }
                                                            }
                                                        } else {
                                                            Log.e("ClickEvent", "Empty or null response body")
                                                        }
                                                    } else {
                                                        Log.e("ClickEvent", "Unsuccessful response: ${response.code()}")
                                                    }
                                                }

                                                override fun onFailure(
                                                    call: Call<List<UnitDetails>>,
                                                    t: Throwable
                                                ) {
                                                    Log.e("ClickEvent", "Network failure. Error: ${t.message}", t)
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
                            //EnterUnit
                            navController.navigate(Screen.EnterUnit.route){
                                popUpTo(Screen.EnterUnit.route){
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