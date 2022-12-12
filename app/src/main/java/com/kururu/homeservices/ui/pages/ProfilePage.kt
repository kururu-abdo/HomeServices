package com.kururu.homeservices.ui.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.kururu.homeservices.data.model.ServiceMan

@Composable
fun ProfilePage(serviceMan: ServiceMan , navController: NavController){

    Column(modifier = Modifier.fillMaxSize().padding(15.dp) ,
    horizontalAlignment = Alignment.CenterHorizontally
        ) {
        
        Image(painter = painterResource(id = serviceMan.avatar), contentDescription ="" ,
            contentScale = ContentScale.Crop, 
            modifier = Modifier
                .clip(CircleShape)

                .border(

                    border = BorderStroke(1.dp, Color.LightGray),

                    CircleShape
                )
            
            )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = serviceMan.workerField, style = TextStyle(
            fontWeight = FontWeight.Bold , fontSize = 18.sp
        
        ))
Row(horizontalArrangement = Arrangement.Center) {
    Icon(Icons.Filled.Star , contentDescription =null ,
    modifier = Modifier.background(Color.Yellow)
        )
    Text(text = serviceMan.rating.toString())
}
        Spacer(modifier = Modifier.height(30.dp))
        Column(verticalArrangement = Arrangement.spacedBy(
            10.dp
        )) {
         
            Box (
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray.copy(alpha = .5f))
                    .padding(10.dp)
                ,
                contentAlignment = Alignment.CenterStart

                    ){
                
                Text(text = serviceMan.name)
                   }


            Box (
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray.copy(alpha = .5f))
                    .padding(10.dp)

                ,
                contentAlignment = Alignment.CenterStart

            ){

                Text(text = "xxx@xxx.com")
            }

            Box (
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray.copy(alpha = .5f))
                    .padding(10.dp)
,
                contentAlignment = Alignment.CenterStart

            ){

                Text(text = "249966302389")
            }


            Box (
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray.copy(alpha = .5f)
                    )  .padding(10.dp) ,
                contentAlignment = Alignment.CenterStart

            ){

                Text(text = "Kassala")
            }
            
            
        }
    }
    
    
    
    
    
}