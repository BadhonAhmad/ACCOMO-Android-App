package com.example.rentcare

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.security.acl.Owner


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


    @POST("owner")
    fun InputOwnerInfo(
        @Body ownerInfo : OwnerInfo
    ):Call<Void>

    @GET("ownerinfo")
    fun GetownerInfo(
        @Query("email") email : String,
        @Query("password") password : String
    ):Call<List<OwnerInfo>>
    //make api get in vs code then connect that api via retrofit where needed

}