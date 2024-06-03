package com.example.rentcare.Data.subscribe

data class SubscribeResponse(
    val statusCode: String,
    val statusDetail: String,
    val subscriptionStatus: String,
    val version: String
)