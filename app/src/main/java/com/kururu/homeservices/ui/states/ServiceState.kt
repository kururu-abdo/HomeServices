package com.kururu.homeservices.ui.states

import com.kururu.homeservices.data.model.ServiceMan
import com.kururu.homeservices.data.model.ServiceModel

data class ServiceState(
    var selectedServiceModel: ServiceModel ?= null ,
    var worker:ServiceMan?= null ,
    var worders:List<ServiceMan> = listOf(),
    var showingFab:Boolean =false
)