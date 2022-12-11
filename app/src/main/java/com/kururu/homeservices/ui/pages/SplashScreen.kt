package com.kururu.homeservices.ui.pages

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kururu.homeservices.R
import com.kururu.homeservices.ui.Destination
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun  SplashScreen(navController: NavController) {
    var visible by remember { mutableStateOf(true) }

    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(

        key1 = Unit, block = {

                if (!visible) {
                    visible = true
                }
            scale.animateTo(
                targetValue = 1.7f,
                animationSpec = tween(
                    durationMillis = 800,
                    easing = {
                        OvershootInterpolator(4f).getInterpolation(it)
                    })
            )
            delay(3000L)

            navController.navigate(Destination.Home.routeName)
        })

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center


    ) {


            Image(
                painter = painterResource(id = R.drawable.icon), contentDescription = "",

                modifier = Modifier.scale(scale.value)


            )


    }


}