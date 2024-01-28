import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rentcare.Components.CTextField
import com.example.rentcare.DropDown
import com.example.rentcare.LoginTextField
import com.example.rentcare.Screen

@Composable
fun LoginScreen(navController : NavController) {
    val gradientColorList = listOf(
        Color(0xFFD0BCFF),
        Color(0xFFEFB8C8),
        Color(0xFFCCC2DC)
    )
    var logmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Surface{//Brushing the Surface of the app
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                brush = GradientBackgroundBrush(
                    isVerticalGradient = true,
                    colors = gradientColorList
                )
            )

        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 16.dp, end = 16.dp),
//                contentAlignment = Alignment.TopStart
//            ) {
//                Text(
//                    modifier = Modifier.padding(top = 40.dp),
//                    text = "RentCare",
//                    fontSize = 30.sp,
//                    fontWeight = FontWeight.ExtraBold,
//                    color = Color.Blue
//                )
//            }
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
            CTextField(
                hint = "Email",
                leadingIcon = Icons.Filled.Email,
                onValueChange = {
                    logmail = it
                }
            )
            CTextField(
                hint = "Password",
                leadingIcon = Icons.Filled.Lock,
                onValueChange = {
                    password = it
                }
            )
//            Spacer(modifier = Modifier.height(20.dp))
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//            ){
//
//            }
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