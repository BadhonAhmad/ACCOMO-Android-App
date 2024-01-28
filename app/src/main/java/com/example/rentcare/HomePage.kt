package com.example.rentcare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun HomePage() {
        Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black
        ) {
                Card(
                        modifier = Modifier.fillMaxSize()

                ){
                        Text("hello")

                }
        }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun HomePagePrev() {
        HomePage()
}