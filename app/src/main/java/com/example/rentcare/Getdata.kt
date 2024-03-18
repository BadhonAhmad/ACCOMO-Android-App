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
    var address : String,
    var mobile : String,
    var email : String,
    var bkash : String,
    val password : String
)
data class FlatInfo (//owner, flatname, floor,unit, rent, gas
    val owner : String,
    val flatname : String,
    val floor : Int,
    val unit : Int,
    val rent : Int,
    val gas : Int
)
data class FlatDetails(
    val code : String,
    val rent : Int,
    val gas : Int
)

data class UnitDetails(
    val name : String,
    val email : String,
    val bkash : String,
    val flatname : String,
    val rent : Int,
    val gas : Int
)
data class RentedFlats(
    var tenant : String,
    var email : String,
    var mobile : String,
    var nid : String,
    var flatname : String,
    var rent : Int,
    var gas : Int
)

data class BillStatus(
    var flatname : String,
    var billstatus: Int
)

data class FlatCode(
    var flatname: String,
    var code : String
)