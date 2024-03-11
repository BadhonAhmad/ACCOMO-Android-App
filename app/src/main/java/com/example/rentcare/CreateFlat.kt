package com.example.rentcare



import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
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


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CreateFlat(navController: NavController) {
    val toastContext = LocalContext.current
    val BASE_URL = "http://192.168.43.186:5001/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
    var femail = MainActivity.ownerInfo?.email ?: ""
    var fname = remember { mutableStateOf(TextFieldValue()) }
    val nfloor = remember { mutableStateOf(TextFieldValue()) }
    val nunit = remember { mutableStateOf(TextFieldValue()) }
    val frent = remember { mutableStateOf(TextFieldValue()) }
    val fgas = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier= Modifier.fillMaxSize()
    ){
        Text(
            text = "Flat's Information :",
            color = Color.Blue,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 100.dp,
                bottom = 8.dp
            )
        )
        OutlinedTextField(
            value =fname.value ,
            onValueChange ={ fname.value=it },
            singleLine = true,
            label = { Text(text = "Flats name") },
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
        OutlinedTextField(
            value =nfloor.value ,
            onValueChange ={ nfloor.value=it},
            singleLine = true,
            label = { Text(text = "Number of Floor") },
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
        OutlinedTextField(
            value =nunit.value ,
            onValueChange ={ nunit.value=it},
            singleLine = true,
            label = { Text(text = "Number of Unit Per Floor") },
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

        OutlinedTextField(
            value =frent.value ,
            onValueChange ={ frent.value=it},
            singleLine = true,
            label = { Text(text = "Rent Bill of Each Unit:") },
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

        OutlinedTextField(
            value =fgas.value ,
            onValueChange ={ fgas.value=it},
            singleLine = true,
            label = { Text(text = "Gas and others Bill of Each Unit:") },
            //colors = TextFieldDefaults.colors(),
            modifier= Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(15.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        CButton(text = "Create Flat", onClick = {
            // Create a RenterInfo object with the entered data
            val flatInfo = FlatInfo(
                owner = femail,
                flatname = fname.value.text,
                floor = nfloor.value.text.toIntOrNull() ?: 0,
                unit = nunit.value.text.toIntOrNull() ?: 0,
                rent = frent.value.text.toIntOrNull() ?: 0,
                gas = fgas.value.text.toIntOrNull() ?: 0
            )


            apiService.InputFlatInfo(flatInfo).enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        navController.navigate(Screen.FindUnit.route){
                            popUpTo(Screen.FindUnit.route){
                                inclusive = true
                            }
                        }
                    } else {
                        val errorMessage = "Error: ${response.code()}"
                        showToast(toastContext,errorMessage)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    val errorMessage = "Failed to update: ${t.localizedMessage}"
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


