package com.kururu.homeservices

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.kururu.homeservices.components.ServiceSchedule
import com.kururu.homeservices.data.model.ServiceModel
import com.kururu.homeservices.di.AppContainer
import com.kururu.homeservices.logic.MainViewModel
import com.kururu.homeservices.ui.Destination
import com.kururu.homeservices.ui.pages.Home
import com.kururu.homeservices.ui.pages.OrderService
import com.kururu.homeservices.ui.pages.ShowService
import com.kururu.homeservices.ui.pages.SplashScreen
import com.kururu.homeservices.ui.theme.HomeServicesTheme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val uri = "https://www.home-service.sd"

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var app = (application as MyApplication)
        val appContainer = (application as MyApplication).appContainer

        setContent {
            val navController = rememberNavController(

            )
            HomeServicesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                  MainScreen(navController ,app)
                }
            }
        }


//        //request work
//        var myRequest :WorkRequest = OneTimeWorkRequestBuilder<ServiceSchedule>().build()
//
//       WorkManager.getInstance(this)
//           .enqueue(myRequest)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen( controller: NavHostController , application: MyApplication) {
    // Create a NavController

    NavHost(navController = controller, startDestination = Destination.SplashSCreen.routeName,  ){
        composable(
            route = Destination.SplashSCreen.routeName

        ){
        SplashScreen(navController = controller)
        }
        composable(
            route = Destination.Home.routeName

        ){
           Home(navController = controller , application=application)
        }
        composable(
            route = Destination.ServiceDetails.routeName+"/{service}" ,

//            arguments = listOf(
//                navArgument("service"){type=NavType.ParcelableType(ServiceModel::class.java)}
//            )


        ){

            val serviceJsonString =  it.arguments?.getString("service")

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter = moshi.adapter(ServiceModel::class.java).lenient()
            val serviceObject = serviceJsonString?.let { it1 -> jsonAdapter.fromJson(it1) }

            ShowService(serviceObject!! , application , navController =controller)
        }
        composable(
            route = Destination.ServiceRequest.routeName
 ,
//            deepLinks = listOf(navDeepLink { uriPattern = "$uri/{id}" })

        ){
          OrderService(navController = controller)
        }
    }


}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HomeServicesTheme {
        Greeting("Android")
    }
}