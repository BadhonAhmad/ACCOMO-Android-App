import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rentcare.ApiService
import com.example.rentcare.Components.CButton
import com.example.rentcare.MainActivity
import com.example.rentcare.OwnerInfo
import com.example.rentcare.RentedFlats
import com.example.rentcare.RenterInfo
import com.example.rentcare.Screen
import com.example.rentcare.YourUnit
import com.example.rentcare.ui.theme.Indigo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController: NavController,
) {
    val toastContext = LocalContext.current

    val notification = rememberSaveable{ mutableStateOf("") }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Sign in",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            var email by remember { mutableStateOf(TextFieldValue()) }
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                singleLine = true,
                label = { Text(text = "Enter your email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(15.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            )

            var password by remember { mutableStateOf(TextFieldValue()) }
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                label = { Text(text = "password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(15.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Sign in as:",
                    fontSize = 25.sp,
                    fontWeight = FontWeight(800)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            CButton(text = "Tenant", onClick = {
                if (email.text.isNotEmpty() && password.text.isNotEmpty()) {
                    val BASE_URL = "http://192.168.43.186:5001/"
                    val apiService: ApiService by lazy {
                        Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(ApiService::class.java)
                    }
                    val getStudentInfoCall: Call<List<RenterInfo>> =
                        apiService.GetrenterInfo(email.text, password.text)

                    getStudentInfoCall.enqueue(object : Callback<List<RenterInfo>> {
                        override fun onResponse(
                            call: Call<List<RenterInfo>>,
                            response: Response<List<RenterInfo>>
                        ) {
                            if (response.isSuccessful) {
                                val resultList: List<RenterInfo>? = response.body()
                                if (resultList != null && resultList.isNotEmpty()) {
                                    MainActivity.renterInfo = resultList.first()
                                    if (email.text.isNotEmpty()) {
                                        // Use the existing apiService instance
                                        val getRentedListCall: Call<List<RentedFlats>> =
                                            apiService.GetRentedList(email.text)


                                        getRentedListCall.enqueue(object : Callback<List<RentedFlats>> {
                                            override fun onResponse(
                                                call: Call<List<RentedFlats>>,
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
                                    navController.navigate(Screen.HomePage.route) {
                                        popUpTo(Screen.HomePage.route) {
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
                            call: Call<List<RenterInfo>>,
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
            CButton(text = "Owner", onClick = {
                if (email.text.isNotEmpty() && password.text.isNotEmpty()){
                    val BASE_URL = "http://192.168.43.186:5001/"
                    val apiService: ApiService by lazy {
                        Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(ApiService::class.java)
                    }
                    val getStudentInfoCall : Call<List<OwnerInfo>> =
                        apiService.GetOwnerInfo(email.text, password.text)

                    getStudentInfoCall.enqueue(object : Callback<List<OwnerInfo>> {
                        override fun onResponse(
                            call: Call<List<OwnerInfo>>,
                            response: Response<List<OwnerInfo>>
                        ) {
                            if (response.isSuccessful) {
                                val resultList: List<OwnerInfo> ?= response.body()
                                if (resultList != null && resultList.isNotEmpty()) {
                                    MainActivity.ownerInfo = resultList.first()
                                    navController.navigate(Screen.FindUnit.route) {
                                        popUpTo(Screen.FindUnit.route) {
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
                            call: Call<List<OwnerInfo>>,
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
            Spacer(modifier = Modifier.height(100.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Don't have an account?",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000113)
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 16.dp),
                onClick = {
                    navController.navigate(Screen.SignUpAs.route) {
                        popUpTo(Screen.SignUpAs.route) {
                            inclusive = true
                        }
                    }
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(
                    text = "Create an Account",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun hj() {
    val navController = rememberNavController()
    Login(navController)
}