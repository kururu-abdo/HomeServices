package com.kururu.homeservices.data.datasources

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.kururu.homeservices.R
import com.kururu.homeservices.data.model.ServiceMan
import com.kururu.homeservices.data.model.ServiceModel
import com.kururu.homeservices.data.model.ServiceType
import com.kururu.homeservices.data.relatedServices
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class LocalDataSource {
  fun getAllWorkers() :List<ServiceMan> {

      var serviceMen = listOf<ServiceMan>(
          ServiceMan(
              name = "John Radford",
              rating = 3.4,
              workerField = "Plumber",
              avatar = R.drawable.engineer
          ) ,
          ServiceMan(
              name = "John Barnes",
              rating = 3.1,
              workerField = "Electrician",
              avatar = R.drawable.engineer
          ) ,
          ServiceMan(
              name = "Kururu",
              rating = 2.8,
              workerField = "Gardener",
              avatar = R.drawable.engineer
          ) ,
          ServiceMan(
              name = "Reem Hammad",
              rating = 4.0,
              workerField = "Painter",
              avatar = R.drawable.engineer
          ) ,


      )

      return  serviceMen
  }


    fun  getRandomWorder () :ServiceMan{
        var random = (0..getAllWorkers().size-1).random()
        var worker = getAllWorkers()[random]

        return  worker
    }







    fun getAllServices () :List<ServiceModel> {

        var homeServices =  listOf<ServiceModel>(

            ServiceModel(
                serviceIcon = R.drawable.clean,
                serviceName = "Cleaning",
                hasSubService = true
            ),

            ServiceModel(
                serviceIcon = R.drawable.painter,
                serviceName = "Painter",
                hasSubService = true
            ),
            ServiceModel(
                serviceIcon = R.drawable.elec,
                serviceName = "Electrician",
                hasSubService = true
            ),
            ServiceModel(
                serviceIcon = R.drawable.plumer,
                serviceName = "Plumber",
                hasSubService = true
            ),

            ServiceModel(
                serviceIcon = R.drawable.garden,
                serviceName = "Landscaping",
                hasSubService = true
            ),
            ServiceModel(
                serviceIcon = R.drawable.concrete,
                serviceName = "Cracked concrete",
                hasSubService = true
            ),
        )

      return homeServices

    }



fun getRelatedService() = relatedServices



  fun getDaysWeek():List<Map<String ,String> >{
      return  listOf(
          mapOf( "name" to "Sat" , "number" to "1"  ) ,
          mapOf( "name" to "Sun" , "number" to "2"  ) ,
          mapOf( "name" to "Mon" , "number" to "3"  ) ,
          mapOf( "name" to "Tue" , "number" to "4"  ) ,
          mapOf( "name" to "Wed" , "number" to "5"  ) ,
          mapOf( "name" to "Thu" , "number" to "6"  ) ,
          mapOf( "name" to "Fri" , "number" to "7"  ) ,
      )
  }


    fun  getTimes() = listOf(
        "08:00" ,"10:00" ,"13:00" ,"03:00"
    )


    @SuppressLint("SimpleDateFormat", "NewApi")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentMonthYear():String {
//        var current = LocalDateTime.now() //  // 2021-12-24T13:15:28.602
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")
//        val dateTime: LocalDateTime = LocalDateTime.parse(current.toString(), formatter)

        val cal: Calendar = Calendar.getInstance()
        val month_date = SimpleDateFormat("MMMM")
        val month_year = SimpleDateFormat("YYYY")

        val month_name = month_date.format(cal.time)
        val year_full = month_year.format(cal.time)

        return "${month_name}   ${year_full} "
    }

fun getRepeats()  = listOf<ServiceType>(
    ServiceType.NOREPEAT  ,
    ServiceType.EVERYDAY  ,
    ServiceType.EVERYWEEK  ,




    )


}