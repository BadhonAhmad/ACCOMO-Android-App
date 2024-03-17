package com.example.rentcare


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentcare.Components.CButton
import com.example.rentcare.ui.theme.Indigo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun FlatProfile(navController: NavController) {
    val notification = rememberSaveable{ mutableStateOf("") }
    val toastContext = LocalContext.current
    var billStatusText by rememberSaveable { mutableStateOf("") }
    val BASE_URL = "http://192.168.43.186:5001/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    if(notification.value.isNotEmpty()){
        Toast.makeText(LocalContext.current,notification.value,Toast.LENGTH_LONG).show()
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

            Icon(
                imageVector = Icons.Default.ArrowBack, // Use the back arrow icon
                contentDescription = null, // Content description can be null for decorative icons
                modifier = Modifier
                    .size(24.dp) // Set the size of the icon
                    .clickable {
                        // Handle back arrow click action (e.g., navigate back)
                        navController.navigate(Screen.FindUnit.route) {
                            popUpTo(Screen.FindUnit.route) {
                                inclusive = true
                            }
                        }
                    }
            )
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "  ${MainActivity.rentedFlats?.flatname} Unit Details",
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .border(2.dp, Color.Blue, shape = RoundedCornerShape(8.dp)),
                    //.background(color = Indigo, shape = RoundedCornerShape(8.dp)),
                    style = TextStyle(
                        fontSize = 25.sp, // Set the font size
                        fontWeight = FontWeight.Bold,
                        color = Color.Blue // Set the text color
                    )
                )
            }

        }
        Row(
        )
        {
            Text(
                text = "Tenant Info",
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
            text = "Name : ${MainActivity.rentedFlats?.tenant}",
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
            text = "Email : ${MainActivity.rentedFlats?.email}",
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
            text = "Mobile : ${MainActivity.rentedFlats?.mobile}",
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
        Row(
        )
        {
            Text(
                text = "Payment Info",
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
            text = "Rent bill: ${MainActivity.rentedFlats?.rent}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 10.dp)
                .padding(8.dp), // Add padding inside the box
            style = TextStyle(
                fontSize = 20.sp, // Set the font size
                fontWeight = FontWeight.Bold,
                color = Color.Black // Set the text color
            )
        )
        Text(
            text = "Gas and others : ${MainActivity.rentedFlats?.gas}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 8.dp)
//                .background(color= SkyBlue,
//                    shape = RoundedCornerShape(8.dp)) // Set the background color of the box
//                .border(
//                    2.dp,
//                    Color.White,
//                    shape = RoundedCornerShape(8.dp)
//                )// Set the border of the box
                .padding(8.dp), // Add padding inside the box
            style = TextStyle(
                fontSize = 20.sp, // Set the font size
                fontWeight = FontWeight.Bold,
                color = Color.Black // Set the text color
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        var isPaid = MainActivity.billStatus?.billstatus == 1
        billStatusText = if (isPaid) "Paid" else "Due"
        var dynamicText = rememberSaveable { mutableStateOf(billStatusText) }
        LaunchedEffect(isPaid) {
            dynamicText.value = if (isPaid) "Paid" else "Due"
        }

        Text(
            text = dynamicText.value,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
        //find summary na thakle unsupported feature yr/mm/dd
        //aage dekhte hbe drive tool or gmail tool ,, qna from content, transliteration is accepted
        //unclear to skip taskid r prompt pashapashi likha jate duplicate na likha hoy

        CButton(
            text = "Mark As Paid",
            onClick = {
                if (MainActivity.rentedFlats != null) {
                    // Create a BillStatus object with the updated bill status
                    val updatedBillStatus = BillStatus(
                        flatname = MainActivity.rentedFlats!!.flatname,
                        billstatus = 1 // Assuming 1 indicates the bill is paid
                    )

                    // Make the PUT request to update the bill status
                    apiService.UpdateBillStatus(updatedBillStatus).enqueue(object : Callback<Void> {
                        override fun onResponse(call: Call<Void>, response: Response<Void>) {
                            if (response.isSuccessful) {
                                billStatusText = "Paid"
                                isPaid = true
                                dynamicText.value = billStatusText
                                // Navigate back to the FindUnit screen on successful update

                            } else {
                                // Handle error
                                val errorMessage = "Error: ${response.code()}"
                                // Show error message to the user
                                showToast(toastContext, errorMessage)
                            }
                        }

                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            val errorMessage = "Failed to update: ${t.localizedMessage}"
                            // Show error message to the user
                            showToast(toastContext, errorMessage)
                        }
                    })
                } else {
                    // Handle case where rentedFlats is null
                    val errorMessage = "Error: Rented flats information not available"
                    showToast(toastContext, errorMessage)
                }
            },
            containerColor = Indigo
        )

    }
}

private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FlatPre() {
    val navController = rememberNavController()
    FlatProfile(navController)
}