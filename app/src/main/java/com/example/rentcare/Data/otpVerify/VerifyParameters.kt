package com.example.rentcare.Data.otpVerify

data class VerifyParameters(
    val appId: String,
    val password: String,
    var referenceNo: String,
    var otp: String
)