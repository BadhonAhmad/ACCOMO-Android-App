package com.example.rentcare

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.rentcare.Components.CButton
import com.example.rentcare.Components.CTextField
import com.example.rentcare.ui.theme.DarkSlateBlue
import com.example.rentcare.ui.theme.Indigo
import com.example.rentcare.ui.theme.SkyBlue

@Composable
fun SignUpScreen(navController: NavController) {
    Surface(
        color = SkyBlue,
        modifier = Modifier.fillMaxSize(),
    ) {
        Box() {
            Image(
                painter = painterResource(id = R.drawable.sign_up_back),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .align(Alignment.TopCenter)
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Column() {
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
                Spacer(modifier = Modifier.height(120.dp))

//                Text(
//                    text = "Sign Up",
//                    style = TextStyle(
//                        fontSize = 25.sp,
//                        fontFamily = FontFamily.SansSerif,
//                        fontWeight = FontWeight(500),
//                        color = Color.Blue
//                    ),
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
            }
        }
        Box(){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 175.dp)
                        .padding(horizontal = 24.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    ProfileImage()
                    CTextField(hint = "Name", value = "")
                    CTextField(hint = "Address", value = "")
                    CTextField(hint = "Email", value = "")
                    CTextField(hint = "Mobile", value = "")
                    CTextField(hint = "password", value = "")
                    CTextField(hint = "Confirm password", value = "")
                    CTextField(hint = "NID number", value = "")
                    CButton(text = "Sign Up")
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
                                navController.navigate(route = Screen.Login.route)
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



