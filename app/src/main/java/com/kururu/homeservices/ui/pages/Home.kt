package com.kururu.homeservices.ui.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow


import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.kururu.homeservices.MyApplication
import com.kururu.homeservices.R
import com.kururu.homeservices.data.homeServices
import com.kururu.homeservices.data.model.ServiceMan
import com.kururu.homeservices.data.model.ServiceModel
import com.kururu.homeservices.logic.MainViewModel
import com.kururu.homeservices.ui.Destination
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class
)
@Composable
fun Home(navController: NavController ,

         application: MyApplication){
    var context = LocalContext.current
    val mainViewModel = application.appContainer.mainViewModel.create()
 var service = mainViewModel.viewState.collectAsState()




    var visible by remember { mutableStateOf(false) }

    var visibleFloatingAction = remember { mutableStateOf(false) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        if (!visible) {
            visible = true
        }




    }




//    LaunchedEffect(mainViewModel.viewFab.value) {
//        visibleFloatingAction.value = mainViewModel.viewFab.value!!
//    }




    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Home Service App") },
                navigationIcon = {
                    Image(painter = painterResource(id = R.drawable.engineer), contentDescription = "" ,

                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)                       // clip to the circle shape
                            .border(0.3.dp, Color.Gray, CircleShape)


                        )
                } ,
                actions = {
                    Icon(Icons.Filled.Notifications, contentDescription = "" ,


                        )
                }
            )


        },



floatingActionButton = {
//    if (service.value.selectedServiceModel == null) {
//        Box() {
//
//        }
//    }

//else{
    AnimatedVisibility(
        visible =
service.value.showingFab
//        true
        ,
        enter = scaleIn() ,

//                + expandVertically(expandFrom = Alignment.CenterVertically),
        // By Default, `scaleOut` uses the center as its pivot point. When used with an
        // ExitTransition that shrinks towards the center, the content will be shrinking both
        // in terms of scale and layout size towards the center.
        exit = scaleOut()

//                + shrinkVertically(shrinkTowards = Alignment.CenterVertically)

    ) {


        FloatingActionButton(onClick = {


            // go to service details

            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter = moshi.adapter<ServiceModel>(ServiceModel::class.java).lenient()
             val serviceToJson  =jsonAdapter.toJson(mainViewModel.viewState.value.selectedServiceModel)
            navController.navigate(Destination.ServiceDetails.routeName+"/${serviceToJson}")


        },
//    modifier = Modifier.background(Color.Blue)
// on below line we are adding
            // background color for our button
            backgroundColor = Color.Blue,
            // on below line we are adding
            // color for our content of fab.
            contentColor = Color.White ,
            modifier = Modifier.alpha(0.5f)
        ) {

            Icon(Icons.Filled.KeyboardArrowRight, "")

            //painter = painterResource(id = R.drawable.ic_arrow_forward), contentDescription =""
        }
   // }

}

                      }



        ,
         content = {
             Column(modifier = Modifier
                 .fillMaxSize()
                 .padding(20.dp)
  ,

         verticalArrangement =         Arrangement.spacedBy(5.dp)
             ) {


                 Row( horizontalArrangement = Arrangement.SpaceBetween ,

                     modifier = Modifier.fillMaxWidth()
                 ) {
                     Text("Recent" ,
                         style = TextStyle(
                             fontWeight = FontWeight.Bold
                         )


                         )

                     Text(text = "view all" ,

                         modifier =Modifier.clickable {
                             //show all
                         } ,
                         style = TextStyle(
                             color = Color.Green ,
                             fontWeight = FontWeight.Bold
                         )
                     )



                 }

AnimatedContent(mainViewModel.viewState) { targetState->
    when (targetState.value.worker) {
        null -> {
            CircularProgressIndicator()
        }
        else -> {
            RecentWorker(serviceMan = targetState.value.worker!! ,navController)
        }
    }
}


//                Text(
//                    text = stringResource(id = R.string.whcihService),
//                    style = TextStyle(fontWeight = FontWeight.Bold ,
//                    fontSize = 36.sp
//                        ,
//                        letterSpacing = 1.5.sp
//
//                        ),
//                    maxLines = 2,
//
//                    )
Row( horizontalArrangement = Arrangement.SpaceBetween ,

    modifier = Modifier.fillMaxWidth()
    ) {
    Text("Categories" ,
        style = TextStyle(
            fontWeight = FontWeight.Bold
        )


        )

    Text(text = "view all" ,

        modifier =Modifier.clickable {
            //show all
        } ,
        style = TextStyle(
        color = Color.Green ,
        fontWeight = FontWeight.Bold
    )
    )



}



                 LazyVerticalGrid( // column count for our grid view.
//                     cells =
                     GridCells.Fixed(3),
                     // on below line we are adding padding
                     // from all sides to our grid view.
                     modifier = Modifier.padding(10.dp) ){
                     items(mainViewModel.getAllServices().size){

if (!visible){
    Box() {
        
    }
}
                         AnimatedVisibility(
                             visible = visible,
                             enter =

                             slideInVertically(
                                 // Start the slide from 40 (pixels) above where the content is supposed to go, to
                                 // produce a parallax effect
                                 initialOffsetY = { -40 * it } ,

                             ) + expandVertically(
                                 expandFrom = Alignment.Top ,
                                 animationSpec = TweenSpec<IntSize>(
                                     durationMillis = it  *1000
                                 )
                             ) + scaleIn(
                                 animationSpec =TweenSpec<Float>(
                                     durationMillis = it  *1000
                                 ),
                                 // Animate scale from 0f to 1f using the top center as the pivot point.
                                 transformOrigin = TransformOrigin(0.5f, 0f)
                             ) + fadeIn(initialAlpha = 0.3f),
                             exit = slideOutVertically() + shrinkVertically() + fadeOut() + scaleOut(targetScale = 1.2f)
                         
                         
                         
                         ) {
                             // Content that needs to appear/disappear goes here:

                             Card(
                                 // inside our grid view on below line we are
                                 // adding on click for each item of our grid view.
                                 onClick = {
                                     // inside on click we are displaying the toast message.

                                     mainViewModel.toggleService(
                                         mainViewModel.getAllServices()[it]
                                     )
                                 },

                                 // on below line we are adding padding from our all sides.
                                 modifier = Modifier
                                     .padding(8.dp)
                                     .height(100.dp)
                                     .width(120.dp)


                                 ,

                                 // on below line we are adding elevation for the card.
                                 elevation = 1.dp
                             ) {
                                 Column(
                                     // on below line we are adding padding
                                     // padding for our column and filling the max size.
                                     Modifier
                                         .fillMaxSize()
                                         .background(
                                             if (service.value.selectedServiceModel == mainViewModel.getAllServices()[it])
                                                 Color.Gray
                                             else Color.Transparent
                                         )
                                         .padding(5.dp) ,


                                     horizontalAlignment = Alignment.CenterHorizontally,
                                     verticalArrangement = Arrangement.Center
                                 ) {
                                     Image(painter = painterResource(id =mainViewModel.getAllServices()[it].serviceIcon), contentDescription ="" )


                                     Spacer(
                                         Modifier.height(5.dp)
                                     )


                                     Text(text = mainViewModel.getAllServices()[it].serviceName ,
                                     overflow = TextOverflow.Ellipsis ,
                                         maxLines = 1
 , style = TextStyle(
                                             fontSize = 12.sp ,

                                             fontWeight = FontWeight.Normal
 )




                                         )
                                 }
                             }
                         }



                     }
                 }





                 Row( horizontalArrangement = Arrangement.SpaceBetween ,

                     modifier = Modifier.fillMaxWidth()
                 ) {
                     Text("Top Rated" ,
                         style = TextStyle(
                             fontWeight = FontWeight.Bold
                         )

                         )

                     Text(text = "view all" ,

                         modifier =Modifier.clickable {
                             //show all
                         } ,
                         style = TextStyle(
                             color = Color.Green ,
                             fontWeight = FontWeight.Bold
                         )
                     )



                 }

WorkersRated(serviceMen = mainViewModel.viewState.value.worders , navController )





             }
         }
            )
}


@Composable
fun  RecentWorker(serviceMan: ServiceMan , navController: NavController) {
Box(  modifier = Modifier

    .fillMaxWidth()
    .height(150.dp)

    .padding(10.dp)
) {
   Card(){

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth()
                ,
            horizontalArrangement = Arrangement.Center ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.engineer), contentDescription =""  ,

                modifier = Modifier
                    .clip(CircleShape),
                        contentScale = ContentScale.Crop,

                )
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = serviceMan.name)

                Text(text = serviceMan.workerField, style = TextStyle(
                    color = Color.Gray
                ))
            }



        }



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .background(Color.Green)
                .clickable {
                    val moshi = Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                    val jsonAdapter = moshi.adapter<ServiceMan>(ServiceMan::class.java).lenient()
                    val serviceToJson  =jsonAdapter.toJson(serviceMan)
                    navController.navigate(Destination.ProfilePageRoue.routeName+"/${serviceToJson}")


                }
            ,
            contentAlignment = Alignment.Center
        ) {

            Text(text = "View Profile" , style = TextStyle(
                color = Color.White
            ) )
        }
    }

       }
}
}

@Composable
fun  WorkersRated(serviceMen: List<ServiceMan> , navController: NavController) {
Box(   
    
    modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    
    ,
    
) {
    LazyRow (
    ){
        itemsIndexed( serviceMen){index, item ->

                WorkerRated(serviceMan = item , navController = navController)


        }
    }
}
}

@Composable
fun  WorkerRated(serviceMan: ServiceMan , navController: NavController){
//    Card(modifier = Modifier .fillMaxHeight() .padding(10.dp) , elevation = 2.dp) {

        Row(
            modifier = Modifier.fillMaxWidth(0.7f)
                .fillMaxHeight()
                .clickable {
                    val moshi = Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                    val jsonAdapter = moshi.adapter<ServiceMan>(ServiceMan::class.java).lenient()
                    val serviceToJson  =jsonAdapter.toJson(serviceMan)
                    navController.navigate(Destination.ProfilePageRoue.routeName+"/${serviceToJson}")

                }
            ,

            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = serviceMan.avatar), contentDescription = "")

            Column(horizontalAlignment = Alignment.Start) {
                Text(text = serviceMan.name)

                Text(
                    text = serviceMan.workerField, style = TextStyle(
                        color = Color.Gray
                    )
                )
            }
            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = serviceMan.rating.toString(), style = TextStyle(
                        fontWeight = FontWeight.Bold
                    )
                )

                Icon(
                    Icons.Filled.Star, contentDescription = "",

                    modifier = Modifier.background(
                        Color.Yellow
                    )
                )
            }


        }

//    }
}