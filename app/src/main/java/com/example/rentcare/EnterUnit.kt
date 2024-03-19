package com.example.rentcare

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
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
import com.example.rentcare.Components.CTextField
import com.example.rentcare.MainActivity.DataManager.rentedFlats
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterUnit(navController: NavController) {
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
    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxHeight() // Fill the available height in the Column
    ) {
        Text(
            modifier = Modifier.padding(top = 100.dp),
            text = "Enter Rented Unit's Code",
            fontSize = 28.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = code,
            onValueChange = { code = it },
            singleLine = true,
            label = { Text(text = "Code") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(15.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        CButton(text = "Check", onClick = {
            if (code.text.isNotEmpty()) {
                // Use the existing apiService instance
                val getStudentInfoCall: Call<List<UnitDetails>> =
                    apiService.GetUnitDetails(code.text)

                getStudentInfoCall.enqueue(object : Callback<List<UnitDetails>> {
                    override fun onResponse(
                        call: Call<List< UnitDetails >>,
                        response: Response<List < UnitDetails >>
                    ) {
                        if (response.isSuccessful) {
                            val resultList: List<UnitDetails>? = response.body()
                            if (resultList != null && resultList.isNotEmpty()) {
                                MainActivity.unitDetails = resultList.first()
                                navController.navigate(Screen.ConfirmUnit.route) {
                                    popUpTo(Screen.ConfirmUnit.route) {
                                        inclusive = true
                                    }
                                }
                            }
                        } else {
                            // Handle the case where the response body is empty or null

                        }
                    }

                    override fun onFailure(
                        call: Call<List<UnitDetails>>,
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
        })
        Spacer(modifier = Modifier.height(10.dp)) // Added space between "Submit" and "Cancel"
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ClickableText(
                text = AnnotatedString("Cancel"),
                onClick = { offset ->
                    navController.navigate(Screen.HomePage.route) {
                        popUpTo(Screen.HomePage.route) {
                            inclusive = true
                        }
                    }
                    // Handle click action for "Cancel"
                    // For now, you can leave it empty or navigate to another destination
                },
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Set the color you desire
                )
            )
        }
    }
}
private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}