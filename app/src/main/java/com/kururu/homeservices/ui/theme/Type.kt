package com.kururu.homeservices.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kururu.homeservices.R
val fonts = FontFamily(
    Font(R.font.poppins_regular) ,
    Font(R.font.poppins_light , weight = FontWeight.Light) ,

    Font(R.font.poppins_bold , weight = FontWeight.Bold) ,
    Font(R.font.poppins_medium , weight = FontWeight.Medium) ,


    )
// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily =fonts ,

    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

