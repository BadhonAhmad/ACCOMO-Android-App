import androidx.annotation.FractionRes
import androidx.annotation.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentcare.DropDown
import com.example.rentcare.LoginTextField
import com.example.rentcare.Screen
import com.example.rentcare.ui.theme.Black

//package com.example.rentcare
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//
//@Composable
//fun LoginScreen(
//    navController: NavController
//)
//    {
//    Column{
//        Text(
//            "RentCare",
//            )
//        TextShow(value = "Sign in",
//            30.sp,
//                Color.Blue
//            )
//        Button(
//            modifier = Modifier.fillMaxWidth(),
//            onClick = {
//                navController.popBackStack()
//            }){
//            Text(text= "Go Back!")
//        }
//    }
//}
//
//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//fun LoginScreenPreview(){
//    LoginScreen(navController = rememberNavController())
//}

@Composable
fun LoginScreen(navController : NavController) {
    val gradientColorList = listOf(
        Color(0xFFD0BCFF),
        Color(0xFFEFB8C8),
        Color(0xFFCCC2DC)
    )
    Surface{//Brushing the Surface of the app
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = GradientBackgroundBrush(
                    isVerticalGradient = true,
                    colors = gradientColorList
                )
            )

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    modifier = Modifier.padding(top = 40.dp),
                    text = "RentCare",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Blue
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = "Sign in",
                    fontSize = 55.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4B0082)
                )
            }
            Spacer(modifier = Modifier.height(35.dp))
            LoginTextField(label = "Email", trailing = "", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(15.dp))
            LoginTextField(label = "Password", trailing = "", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
            ){

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Sign in as:",
                    fontSize = 20.sp
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
                DropDown()
            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 16.dp),
                onClick = {},
                shape = RoundedCornerShape(4.dp)
            ){
                Text(text = "Log in", fontWeight = FontWeight.Medium)
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
                onClick = {},
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ){
                Text(text = "Create an  Account",
                    fontWeight = FontWeight.Medium ,
                            color = Color.White
                    )
            }

        }

    }
}

@Composable
fun GradientBackgroundBrush(
    isVerticalGradient : Boolean,
    colors : List<Color>
): Brush {
    val endOffset = if(isVerticalGradient){
        Offset(0f,Float.POSITIVE_INFINITY)
    }else{
        Offset(Float.POSITIVE_INFINITY,0f)
    }
    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = endOffset
    )
}