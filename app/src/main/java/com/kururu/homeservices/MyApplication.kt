package com.kururu.homeservices

import android.app.Application
import com.kururu.homeservices.di.AppContainer

class MyApplication :Application() {

    val appContainer = AppContainer()

}