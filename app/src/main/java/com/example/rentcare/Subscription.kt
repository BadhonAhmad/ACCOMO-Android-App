package com.example.rentcare

import android.util.Log
import com.example.rentcare.ApiService
import com.example.rentcare.ServiceBuilder
import com.example.rentcare.Data.subscribe.SubscribeRequestParameters
import com.example.rentcare.Data.subscribe.SubscribeResponse
import com.example.rentcare.Data.unsubscribe.UnsubscribeRequestParameters
import com.example.rentcare.Data.unsubscribe.UnsubscribeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun subscriptionOn() {
    val subscribeRequestParameters = SubscribeRequestParameters(
        appId = "APP_119016",
        password = "869a9f8f973ac130f4f578e88f004b06",
        mobile = "8801603252292"
    )
    val destinationService = ServiceBuilder.buildService(ApiService::class.java)
    val requestCall = destinationService.subscribe(subscribeRequestParameters)

    requestCall.enqueue(object : Callback<SubscribeResponse> {
        override fun onResponse(
            call: Call<SubscribeResponse>,
            response: Response<SubscribeResponse>
        ) {
            if (response.isSuccessful) {
                val apiResponse = response.body()
                Log.d("MyActivity", "Subscription request sent successfully: $apiResponse")
            } else {
                // Handle unsuccessful response
                Log.e("MyActivity", "Failed to send subscription request: ${response.errorBody()?.string()}")
            }
        }
        override fun onFailure(call: Call<SubscribeResponse>, t: Throwable) {
            // Handle failure
            Log.e("MyActivity", "Network error: ${t.message}")
        }
    })
}

fun subscriptionOff() {
    val unsubscribeRequestParameters = UnsubscribeRequestParameters(
        appId = "APP_119016",
        password = "869a9f8f973ac130f4f578e88f004b06",
        mobile = "8801603252292"
    )
    val destinationService = ServiceBuilder.buildService(ApiService::class.java)
    val requestCall = destinationService.unsubscribe(unsubscribeRequestParameters)

    requestCall.enqueue(object : Callback<UnsubscribeResponse> {
        override fun onResponse(
            call: Call<UnsubscribeResponse>,
            response: Response<UnsubscribeResponse>
        ) {
            if (response.isSuccessful) {
                val apiResponse = response.body()
                Log.d("MyActivity", "Unsubscription request sent successfully: $apiResponse")
            } else {
                // Handle unsuccessful response
                Log.e("MyActivity", "Failed to send unsubscription request: ${response.errorBody()?.string()}")
            }
        }
        override fun onFailure(call: Call<UnsubscribeResponse>, t: Throwable) {
            // Handle failure
            Log.e("MyActivity", "Network error: ${t.message}")
        }
    })
}