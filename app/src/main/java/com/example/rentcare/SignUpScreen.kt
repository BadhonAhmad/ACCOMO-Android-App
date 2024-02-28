package com.example.rentcare

import android.content.ContentValues.TAG
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.rentcare.Components.CButton
import com.example.rentcare.ui.theme.Indigo
import com.example.rentcare.ui.theme.SkyBlue
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController
) {
    val toastContext = LocalContext.current
    val BASE_URL = "http://192.168.43.186:5001/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    var nam = remember { mutableStateOf(TextFieldValue()) }
    var address = remember { mutableStateOf(TextFieldValue()) }
    var email = remember { mutableStateOf(TextFieldValue()) }
    var mobile = remember { mutableStateOf(TextFieldValue()) }
    var password = remember { mutableStateOf(TextFieldValue()) }
   // var password by remember { mutableStateOf("") }
    //var con_password by remember { mutableStateOf("") }
    var nid = remember { mutableStateOf(TextFieldValue()) }
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Sign Up",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight(500),
                        color = Indigo
                    ),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                //Spacer(modifier = Modifier.height(120.dp))
            }
        }
        Box(){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                        .padding(top = 50.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    ProfileImage()
                    OutlinedTextField(
                        value =nam.value ,
                        onValueChange ={ nam.value=it},
                        singleLine = true,
                        label = { Text(text = "Enter your name") },
                        //colors = TextFieldDefaults.colors(),
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                        shape = RoundedCornerShape(15.dp),

                    )
                    OutlinedTextField(
                        value =address.value ,
                        onValueChange ={ address.value=it},
                        singleLine = true,
                        label = { Text(text = "Enter your name") },
                        //colors = TextFieldDefaults.colors(),
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                        shape = RoundedCornerShape(15.dp),

                        )
                    OutlinedTextField(
                        value =email.value ,
                        onValueChange ={ email.value=it},
                        singleLine = true,
                        label = { Text(text = "Enter your name") },
                        //colors = TextFieldDefaults.colors(),
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                        shape = RoundedCornerShape(15.dp),

                        )
                    OutlinedTextField(
                        value =mobile.value ,
                        onValueChange ={ mobile.value=it},
                        singleLine = true,
                        label = { Text(text = "Enter your name") },
                        //colors = TextFieldDefaults.colors(),
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                        shape = RoundedCornerShape(15.dp),

                        )
                    OutlinedTextField(
                        value =password.value ,
                        onValueChange ={ password.value=it},
                        singleLine = true,
                        label = { Text(text = "Enter your name") },
                        //colors = TextFieldDefaults.colors(),
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                        shape = RoundedCornerShape(15.dp),

                        )
                    OutlinedTextField(
                            value =nid.value ,
                    onValueChange ={ nid.value=it},
                    singleLine = true,
                    label = { Text(text = "Enter your name") },
                    //colors = TextFieldDefaults.colors(),
                    modifier= Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
                    shape = RoundedCornerShape(15.dp),

                    )

                    CButton(text = "Sign Up", onClick = {
                        // Create a RenterInfo object with the entered data
                        val renterInfo = RenterInfo(
                            name = nam.value.text,
                            address = address.value.text,
                            email = email.value.text,
                            mobile = mobile.value.text,
                            password = password.value.text,
                            nid =nid.value.text
                        )
                        // Make the PUT request
                        apiService.InputRenterInfo(renterInfo).enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    navController.navigate(Screen.HomePage.route){
                                        popUpTo(Screen.HomePage.route){
                                            inclusive = true
                                        }
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

                    })

                    Row(modifier = Modifier.padding(top = 12.dp,bottom = 40.dp)) {
                        Text("Already have an account?",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        )
                        Text("Sign in",
                            style= TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight(800),
                                color = Color.Black
                            ),
                            modifier = Modifier.clickable {
                                navController.navigate(Screen.Login.route){
                                    popUpTo(Screen.Login.route){
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }
        }
    }
}

@Composable
fun ProfileImage() {
    val imageUri = rememberSaveable{ mutableStateOf("") }
    val painter = rememberImagePainter(
        if(imageUri.value.isEmpty())
            R.drawable.ic_image
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){
        uri : Uri?->
        uri ?.let{imageUri.value = it.toString()}
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
            ){
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .wrapContentSize()
                    .clickable {
                        launcher.launch("image/*")
                    },
               contentScale = ContentScale.Crop
            )
        }
        Text(text = "Change Profile Picture")
    }

}

fun createUserInFireBase(email:String, password : String){
    try {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User created successfully")
                } else {
                    Log.e(TAG, "User creation failed: ${task.exception?.message}")
                }
            }
    } catch (e: Exception) {
        Log.e(TAG, "Exception during user creation: ${e.message}")
    }
}
private fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
//@Preview(showSystemUi = ture,)
//@Composable
//fun SignUpScreenP(navController: NavController) {
//    SignUpScreenP(navController = NavController())
//}



