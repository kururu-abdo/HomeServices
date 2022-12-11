package com.kururu.homeservices.ui.pages

import android.graphics.Insets.add
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.ComponentRegistry
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.kururu.homeservices.R
import com.kururu.homeservices.ui.Destination

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun OrderService(navController: NavController,


                 ) {

var context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {


        Image(

            painter = rememberImagePainter(
                imageLoader=imageLoader,
                data = R.drawable.done), contentDescription = "",
            Modifier
                .scale(0.5f)
                .padding(vertical = 10.dp)


        )
        Text(text = "Service Ordered Successfully!")


        Button(
            onClick = { navController.navigate(Destination.Home.routeName) },

            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
            modifier = Modifier.width(150.dp)

            ) {
            Text(text = "Go Home" , style = TextStyle(
                color = Color.White
            ))
        }


    }
}