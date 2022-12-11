package com.kururu.homeservices.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.kururu.homeservices.data.datasources.LocalDataSource
import com.kururu.homeservices.data.repository.MainRepository
import com.kururu.homeservices.logic.DetailsViewModel

class AppContainer {

    //local or remote sources here


    // Since you want to expose userRepository out of the container, you need to satisfy
    // its dependencies as you did before
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("https://example.com")
//        .build()
//        .create(LoginService::class.java)
//
//    private val remoteDataSource = UserRemoteDataSource(retrofit)
    private val localDataSource = LocalDataSource()


    private  var mainRepository = MainRepository(
        localDataSource
    )

   var mainViewModel =MainViewModelFactory(mainRepository )

    @RequiresApi(Build.VERSION_CODES.O)
    var detailsViewModel =DetailsViewModelFactory(mainRepository )


}