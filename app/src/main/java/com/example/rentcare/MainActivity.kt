package com.example.rentcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseApp


class MainActivity : ComponentActivity() {
    companion object DataManager {
        // Variables to store student information, result list, and semester
        var renterInfo: RenterInfo? = null
        var ownerInfo : OwnerInfo ? = null
        var flatInfo : FlatInfo ? = null
        var flatDetails : FlatDetails ? = null
        var unitDetails : UnitDetails ? = null
        var rentedFlats : RentedFlats ? = null
        var rentedList : List<RentedFlats> ?= emptyList()
        var billStatus : BillStatus ?= null
      //var resultList: List<Getdata>?= null
      //var semester:String?=null
    }
    lateinit var navController : NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navController = rememberNavController()
            SetUpNavGraph(navController = navController)
        }
    }
}

/*
1.make all the screens individually
2.
*/