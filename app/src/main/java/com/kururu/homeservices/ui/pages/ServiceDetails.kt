package com.kururu.homeservices.ui.pages

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kururu.homeservices.MyApplication
import com.kururu.homeservices.R
import com.kururu.homeservices.data.model.ServiceModel
import com.kururu.homeservices.logic.DetailsEvent
import com.kururu.homeservices.ui.Destination
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalAnimationApi::class)


@Composable
fun  ShowService(serviceModel: ServiceModel ,
                 application: MyApplication,
                 navController: NavController
                 ){
val  scaffoldState = rememberScaffoldState()
    val detailsViewModel = application.appContainer.detailsViewModel.create()
    var viewState = detailsViewModel.viewState.collectAsState()
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!visible) {
            visible = true
        }




    }


    Scaffold(scaffoldState = scaffoldState,



        floatingActionButton = {
//    if (service.value.selectedServiceModel == null) {
//        Box() {
//
//        }
//    }

//else{
            AnimatedVisibility(
                visible =viewState.value.selectedDay != null &&
                        viewState.value.selectedRepeate != null &&
                        viewState.value.selectedTime != null

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

                    navController.navigate(Destination.ServiceRequest.routeName)


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
                    Column( modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            vertical = 50.dp,
                            horizontal = 5.dp,

                            )


                    ) {

                        Text(text = "Select Date \n and Time" , style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp
                        ))
                        Spacer(modifier = Modifier.height(20.dp))


                        Row(modifier = Modifier.fillMaxWidth() ,

                            horizontalArrangement = Arrangement.SpaceBetween

                            ) {

                            Text(text = viewState.value.monthAndYear!!)


                            Icon(painter = painterResource(id = R.drawable.stop_circle), "")




                        }

                        Card(elevation = .5.dp , modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)

                        ) {

                            LazyRow(
horizontalArrangement = Arrangement.SpaceEvenly ,


                                ){
                                itemsIndexed(detailsViewModel.viewState.value.days) { index, item ->

                                    AnimatedVisibility(



                                        visible = visible,
                                        enter = slideInVertically(
                                            // Start the slide from 40 (pixels) above where the content is supposed to go, to
                                            // produce a parallax effect
                                            initialOffsetY = { -40 },
                                            animationSpec = TweenSpec<IntOffset>(
                                                durationMillis = index  *1000
                                            )
                                        ) + expandVertically(
                                            expandFrom = Alignment.Top
                                        ) + scaleIn(
                                            // Animate scale from 0f to 1f using the top center as the pivot point.
                                            transformOrigin = TransformOrigin(0.5f, 0f)
                                        ) + fadeIn(initialAlpha = 0.3f ,


                                            ),
                                        exit = slideOutVertically() + shrinkVertically() + fadeOut() + scaleOut(targetScale = 1.2f)

                                    ) {


                                    Column(

                                        modifier = Modifier
                                            .fillMaxWidth(
                                                fraction = 0.7f,


                                                )
                                            .clip(
                                                RoundedCornerShape(10.dp)
                                            )
                                            .background(
                                                with(detailsViewModel.viewState.value.selectedDay) {
                                                    if (this == null) {

                                                        Color.Transparent
                                                    } else {


                                                        if (this.get(
                                                                "number"
                                                            )

                                                            ==
                                                            item
                                                                .get("number")
                                                                .toString()
                                                        ) {
                                                            Color.Yellow
                                                        } else {
                                                            Color.Transparent
                                                        }
                                                    }
                                                }


                                            )
                                            .padding(8.dp)

                                            .clickable {


                                                detailsViewModel.onEvent(
                                                    DetailsEvent.SelectDay(item)
                                                )


                                            },


                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Text(
                                            item.get("number").toString(),

                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 20.sp
                                            )
                                        )

                                        Text(item.get("name").toString())


                                    }


                                }
                                }
                            }


                        }
                        Card(elevation = .5.dp , modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(vertical = 10.dp)

                        ){


                            LazyRow(
horizontalArrangement = Arrangement.SpaceAround
                            ){
                                itemsIndexed(detailsViewModel.viewState.value.times){ index, item->
                                    Box(

                                        modifier = Modifier
                                            .fillMaxWidth(
                                                fraction = 0.5f,


                                                )
                                            .height(40.dp)
                                            .padding(5.dp)

                                            .clip(
                                                RoundedCornerShape(
                                                    20.dp
                                                )
                                            )

                                            .background(

                                                with(detailsViewModel.viewState.value.selectedTime) {
                                                    if (this == null) {
                                                        Color.Transparent
                                                    } else {


                                                        if (this

                                                            ==
                                                            item.toString()
                                                        ) {
                                                            Color.Green
                                                        } else {
                                                            Color.Transparent
                                                        }

                                                    }
                                                }


                                            )

                                            .clickable {
                                                detailsViewModel.onEvent(
                                                    DetailsEvent.SelectTime(item)
                                                )
                                            }
                                    ) {
                                        Text(item.toString() ,

                                            style = TextStyle(fontWeight = FontWeight.Bold ,
                                                fontSize = 20.sp
                                            )
                                        )


                                    }

                                }
                            }


                        }





                        Text(text = "Repeat")



                        LazyRow(
                            modifier=Modifier.fillMaxWidth(),
horizontalArrangement = Arrangement.SpaceAround
                        ){
                            itemsIndexed(detailsViewModel.viewState.value.repeates){ index, item->
                                Box(

                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(60.dp)


                                        .clip(
                                            RoundedCornerShape(
                                                20.dp
                                            )
                                        )

                                        .background(

                                            with(detailsViewModel.viewState.value.selectedRepeate) {
                                                if (this == null) {


                                                    Color.Transparent
                                                } else {

                                                    if (this

                                                        ==
                                                        item.name.toString()
                                                    ) {
                                                        Color.Yellow
                                                    } else {
                                                        Color.Transparent
                                                    }
                                                }
                                            }


                                        )

                                        .clickable {
                                            detailsViewModel.onEvent(
                                                DetailsEvent.SelectRepeat(item)
                                            )
                                        } ,

                                    contentAlignment = Alignment.Center
                                ) {

                                       Text(item.name.toString() ,

                                           style = TextStyle(fontWeight = FontWeight.Bold ,
                                               fontSize = 20.sp
                                           )
                                       )






                                }

                            }
                        }








                        Text(text = "Additional Services" , modifier = Modifier.padding(
                            vertical = 10.dp
                        ))
LazyRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceAround
){
    itemsIndexed(detailsViewModel.viewState.value.relatedServices){
        index, item ->

        Card(elevation = 2.dp ,
            modifier = Modifier
                .fillMaxWidth(fraction = .5f)
                .height(90.dp)


                .clickable {

                    val moshi = Moshi
                        .Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                    val jsonAdapter = moshi
                        .adapter<ServiceModel>(ServiceModel::class.java)
                        .lenient()
                    val serviceToJson = jsonAdapter.toJson(item)
                    navController.navigate(Destination.ServiceDetails.routeName + "/${serviceToJson}")

                }

        ) {
            Column(
                modifier= Modifier.padding(
horizontal = 5.dp
                ) ,
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = item.serviceIcon), contentDescription = "")

                Text(text = "50 SDG")

            }
        }


    }
}


                    }
                }






        )








}