package com.example.rentcare

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    // Define a function for the PUT operation
    @POST("renter")
    fun InputRenterInfo(
        @Body renterInfo: RenterInfo // Request body containing updated RenterInfo
    ): Call<Void> // Call object to handle the response
//    @GET("renterinfo")
//    fun GetrenterInfo(
//
//    ):Call<RenterInfo>
}