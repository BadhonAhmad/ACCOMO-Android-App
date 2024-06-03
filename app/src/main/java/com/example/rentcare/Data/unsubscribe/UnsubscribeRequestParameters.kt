package com.example.rentcare.Data.unsubscribe

data class UnsubscribeRequestParameters(
    val appId: String,
    val password: String,
    val mobile: String
)