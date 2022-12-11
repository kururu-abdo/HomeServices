package com.kururu.homeservices.ui.states

import com.kururu.homeservices.data.model.ServiceModel
import com.kururu.homeservices.data.model.ServiceType

data class ServiceDetailsState(
    var selectedTime:String?=null,
    var selectedRepeate:String?=null,
    var times:List<String>  =listOf(),

    var days:List<Map<String ,String>> =listOf(),
    var selectedDay:Map<String ,String>?=null,
var  relatedServices:List<ServiceModel> = listOf() ,
    var repeates:List<ServiceType> = listOf(),
    var monthAndYear:String?=null,

    ) {
}