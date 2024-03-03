package com.example.rentcare

import androidx.compose.runtime.MutableState
import androidx.compose.ui.text.input.TextFieldValue


data class RenterInfo(
    // Represents a data object containing information about a renterinfo.
    val name: String,
    val address: String,
    val email: String,
    val mobile: String,
    val password: String,
    val nid: String
)

data class OwnerInfo(
    val name : String,
    val address : String,
    val mobile : String,
    val email : String,
    val bkash : String,
    val password : String
)