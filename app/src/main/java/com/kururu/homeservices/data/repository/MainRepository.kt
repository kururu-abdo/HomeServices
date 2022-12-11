package com.kururu.homeservices.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.kururu.homeservices.data.datasources.LocalDataSource
import com.kururu.homeservices.data.homeServices
import com.kururu.homeservices.data.relatedServices

class MainRepository (var localDataSource:LocalDataSource){


  fun   getAllServerice( ) = localDataSource.getAllServices()
  fun   getAllWorders( ) = localDataSource.getAllWorkers()
fun getRandomWorder() =localDataSource.getRandomWorder()


  fun getRelatedService() = localDataSource.getRelatedService()



  fun getDaysWeek()=localDataSource.getDaysWeek()

  @RequiresApi(Build.VERSION_CODES.O)
  fun  getMonthAndYear ()=localDataSource.getCurrentMonthYear()

  fun getTimes()= localDataSource.getTimes()

  fun getRepeats()= localDataSource.getRepeats()

}