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

    @POST("owner")
    fun InputOwnerInfo(
        @Body ownerInfo: OwnerInfo // Request body containing updated RenterInfo
    ): Call<Void>

    @POST("rented_flats")
    fun InputRentedFlats(
        @Body rentedFlats: RentedFlats
    ):Call < Void >

    @POST("createflat")
    fun InputFlatInfo(
        @Body flatInfo: FlatInfo
    ):Call<Void>

    @GET("renterinfo")
    fun GetrenterInfo(
        @Query("email") email:String,
        @Query("password") password:String
    ):Call<List<RenterInfo>>

    @GET("ownerinfo")
    fun GetOwnerInfo(
        @Query("email") email:String,
        @Query("password") password:String
    ):Call<List<OwnerInfo>>

    @GET("flatdetails")
    fun GetFlatDetails(
        @Query("flatname") flatname : String
    ):Call<List<FlatDetails>>

    @GET("unitdetails")
    fun GetUnitDetails(
        @Query("code") code : String
    ):Call<List<UnitDetails>>

    @GET("specificunit")
    fun GetSpecificUnit(
        @Query("flatname") flatname: String
    ):Call<List<UnitDetails>>


    @GET("rentedlist")
    fun GetRentedList(
        @Query("email") email : String
    ):Call<List<RentedFlats>>


}