package com.example.rentcare


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.rentcare.Components.CButton
import com.example.rentcare.ui.theme.Indigo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//import androidx.wear.compose.material.ContentAlpha

@Composable
fun ClickableIcon(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
//    tint: Color = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        content = {
            Icon(
                imageVector = icon,
                contentDescription = null
                //tint = tint
            )
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindUnit(navController: NavController) {
    val toastContext = LocalContext.current
    var fname by remember { mutableStateOf(TextFieldValue()) }
    var owner by remember { mutableStateOf(TextFieldValue(MainActivity.ownerInfo?.email ?: "")) }

    Column(
        modifier = Modifier.fillMaxSize()

    ) {
        Row(

        )
        {
            Text(
                text = "Home Page",
                color = Color.Blue,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                )
            )
            Spacer(modifier = Modifier.width(200.dp))
            ClickableIcon(
                icon = Icons.Default.Notifications,
                onClick = {
                    // Handle icon click event
                },
                modifier = Modifier.size(50.dp),
                //tint = Color.Red
            )

            ClickableIcon(
                icon = Icons.Default.Person,
                onClick = {
                    // Handle icon click event
                },
                modifier = Modifier.size(50.dp),
                //tint = Color.Red
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(modifier = Modifier.width(100.dp))
            RoundedButton(
                modifier = Modifier.padding(top = 40.dp,end = 16.dp),
                text = "Create Apartment",
                buttonColor = Color.Black,
                textColor = Color.White,
                height = 48.dp,
                width = 150.dp,
                onClick = {
                    navController.navigate(Screen.CreateFlat.route){
                        popUpTo(Screen.CreateFlat.route){
                            inclusive = true
                        }
                    }
                }
            )
        }
        Text(
            text = "Find a Unit :",
            color = Color.Blue,
            fontSize = 40.sp,
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
            value =fname ,
            onValueChange ={ fname=it},
            singleLine = true,
            label = { Text(text = "Flats Name :(eg. 'Home-1-A'") },
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

        CButton(text = "Find Unit", onClick = {
            if (fname.text.isNotEmpty()) {
                val BASE_URL = "http://192.168.43.186:5001/"
                val apiService: ApiService by lazy {
                    Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ApiService::class.java)
                }
                val getStudentInfoCall : Call<List<FlatDetails>> =
                    apiService.GetFlatDetails(fname.text)

                getStudentInfoCall.enqueue(object : Callback<List<FlatDetails>> {
                    override fun onResponse(
                        call: Call<List<FlatDetails>>,
                        response: Response<List<FlatDetails>>
                    ) {
                        if (response.isSuccessful) {
                            val resultList: List<FlatDetails> ?= response.body()
                            if (resultList != null && resultList.isNotEmpty()) {
                                MainActivity.flatDetails = resultList.first()
                                Log.d("Navigation", "Navigating from FindUnit to FlatProfile")
                                navController.navigate(Screen.FlatProfile.route) {
                                    popUpTo(Screen.FlatProfile.route) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                // Handle the case where the response body is empty or null
                            }
                        } else {
                            try {
                                // ... Your existing code ...
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Toast.makeText(toastContext, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                            }

                        }
                    }

                    override fun onFailure(
                        call: Call<List<FlatDetails>>,
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
                try {
                    // ... Your existing code ...
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(toastContext, "An error occurred: ${e.message}", Toast.LENGTH_SHORT).show()
                }

            }
        },
            containerColor = Indigo
        )
    }
}