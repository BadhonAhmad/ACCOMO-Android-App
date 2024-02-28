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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentcare.ApiService
import com.example.rentcare.Components.CTextField
import com.example.rentcare.DropDown
import com.example.rentcare.MainActivity
import com.example.rentcare.RenterInfo
import com.example.rentcare.Screen
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController : NavController,
) {
    val toastContext = LocalContext.current
  //  var logmail by remember { mutableStateOf("") }
  //  var password by remember { mutableStateOf("") }
    Surface{//Brushing the Surface of the app
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
        ) {

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
                    color = Color(0xFF4B0082)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            var email = remember { mutableStateOf(TextFieldValue()) }
            OutlinedTextField(
                value =email.value ,
                onValueChange ={ email.value=it},
                singleLine = true,
                label = { Text(text = "Enter your email") },
            //    colors = TextFieldDefaults.colors(),
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(15.dp),
              /*  keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.None
                )*/

            )

          //  Textrow1(text = "Date of Birth : ",10.dp)
            //Spacer(modifier = Modifier.height(10.dp))
            var password = remember { mutableStateOf(TextFieldValue()) }
            OutlinedTextField(
                value =password.value ,
                onValueChange ={ password.value=it},
                singleLine = true,
                label = { Text(text = "password") },
                //            colors = TextFieldDefaults.colors()
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(15.dp),
              //  colors = TextFieldDefaults.colors(),
               /* keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.None
                )
            )       */
            )
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

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
            ) {
                DropDown()
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    if(email.value.text.isNotEmpty() and password.value.text.isNotEmpty()){
                        val BASE_URL = "http://192.168.43.186:5001/"
                        val apiService: ApiService by lazy {
                            Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(ApiService::class.java)
                        }
                   //     val email = email.value.text
                        val getStudentInfoCall: Call<List<RenterInfo>> =
                            apiService.GetrenterInfo(
                                email.value.text,
                                password.value.text
                            )

                        getStudentInfoCall.enqueue(object : Callback<List<RenterInfo> >{
                            override fun onResponse(
                                call: Call<List<RenterInfo>>,
                                response: Response<List<RenterInfo>>
                            ) {
                                if (response.isSuccessful) {
                                    // Handle successful response
                                    val resultList:List< RenterInfo>? = response.body()
                                    if (resultList != null && resultList.isNotEmpty()) {
                                        MainActivity.renterInfo = resultList.first()
                                      //  navController.navigate("resultpage")
                                        navController.navigate(Screen.Profile.route){
                                            popUpTo(Screen.HomePage.route){
                                                inclusive = true
                                            }
                                        }
                                    } else {
                                        // Handle the case where the response body is empty or null
                                        // You might want to show an error message or take appropriate action
                                    }
                                } else {
                                    // Handle unsuccessful response
                                    Toast.makeText(toastContext, "API call failed. Code: ${response.code()}", Toast.LENGTH_SHORT).show()
                                }


                                // Always navigate to "resultpage" whether the API call was successful or not
                            }

                            override fun onFailure(
                                call: Call<List<RenterInfo>>,
                                t: Throwable
                            ) {
                                // Handle network failure
                                // You might want to show an error message or take appropriate action
                                Toast.makeText(toastContext, "Network failure. Error: ${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })}
                    else{
                        Toast.makeText(toastContext,"Please Insert All The Value",Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Gray),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 20.sp
                    // fontWeight = FontWeight.ExtraBold  // Uncomment this line if you want to set the font weight
                )
            }


            Spacer(modifier = Modifier.height(50.dp))

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
                    navController.navigate(Screen.SignUpScreen.route){
                        popUpTo(Screen.SignUpScreen.route){
                            inclusive = true
                        }
                    }
                },
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ){
                Text(text = "Create an  Account",
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}
