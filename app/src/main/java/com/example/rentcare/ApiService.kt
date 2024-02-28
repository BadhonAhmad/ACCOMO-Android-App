package com.example.rentcare

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    // Define a function for the PUT operation
    @POST("renter")
    fun InputRenterInfo(
        @Body renterInfo: RenterInfo // Request body containing updated RenterInfo
    ): Call<Void> // Call object to handle the response
    @GET("renterinfo")
    fun GetrenterInfo(
        @Query("email") email:String,
        @Query("password") password:String
    ):Call<List<RenterInfo>>
}