package com.kururu.homeservices.data

import android.app.Service
import com.kururu.homeservices.R
import com.kururu.homeservices.data.model.ServiceModel

var homeServices =  listOf<ServiceModel>(

    ServiceModel(
        serviceIcon = R.drawable.clean,
        serviceName = "Cleaning" ,
        hasSubService = true
    )
 ,

    ServiceModel(
        serviceIcon = R.drawable.painter,
        serviceName = "Painter" ,
        hasSubService = true
    ) ,
    ServiceModel(
        serviceIcon = R.drawable.elec,
        serviceName = "Electrician" ,
        hasSubService = true
    ),
    ServiceModel(
        serviceIcon = R.drawable.plumer,
        serviceName = "Plumber" ,
        hasSubService = true
    ),

    ServiceModel(
        serviceIcon = R.drawable.garden,
        serviceName = "Landscaping" ,
        hasSubService = true
    ),
    ServiceModel(
        serviceIcon = R.drawable.concrete,
        serviceName = "Cracked concrete" ,
        hasSubService = true
    ),
    )


var relatedServices  = listOf<ServiceModel>(

    ServiceModel(
        serviceIcon = R.drawable.clean,
        serviceName = "Cleaning" ,
        hasSubService = true
    )
    ,

    ServiceModel(
        serviceIcon = R.drawable.painter,
        serviceName = "Painter" ,
        hasSubService = true
    ) ,
    ServiceModel(
        serviceIcon = R.drawable.elec,
        serviceName = "Electrician" ,
        hasSubService = true
    ),
)