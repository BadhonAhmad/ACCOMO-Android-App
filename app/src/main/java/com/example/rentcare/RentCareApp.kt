package com.example.rentcare

import android.app.Application
import com.google.firebase.FirebaseApp

class RentCareApp : Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}