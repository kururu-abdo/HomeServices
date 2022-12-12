package com.kururu.homeservices.ui

import android.os.Parcelable


// 1. Create a destination
sealed class Destination(val routeName:String)  { // parcelable so that it can be saved in a bundle.
     object Home : Destination( routeName = "/")
    object ServiceDetails : Destination("details")
    object ServiceRequest : Destination("done")
    object ProfilePageRoue : Destination("profile")


    object SplashSCreen : Destination("splash")
}