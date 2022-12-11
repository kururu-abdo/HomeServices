package com.kururu.homeservices.logic

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kururu.homeservices.data.datasources.LocalDataSource
import com.kururu.homeservices.data.model.ServiceType
import com.kururu.homeservices.data.repository.MainRepository
import com.kururu.homeservices.ui.states.ServiceDetailsState
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_OFF
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class DetailsViewModel (var repo: MainRepository):ViewModel() {
    private   var _viewState = MutableStateFlow(ServiceDetailsState())

    var viewState  = _viewState.asStateFlow()


    init {

        viewModelScope.launch {
            _viewState.update {
                    state -> state.copy(
                times =repo.getTimes(),
                days =repo.getDaysWeek(),
                repeates =repo.getRepeats(),
                relatedServices =repo.getRelatedService(),
                monthAndYear = repo.getMonthAndYear()




            )
            }
        }

    }

fun onEvent(event: DetailsEvent){
    when(event){
        is DetailsEvent.SelectDay ->{




                _viewState.update {
                        state -> state.copy(

                    selectedDay = event.day
                )}

        }
        is DetailsEvent.SelectRepeat ->{



//            if (_viewState.value.selectedDay!= null){
//                _viewState.update {
//                        state -> state.copy(
//
//                    selectedRepeate = null
//                )}
//            }else {
                _viewState.update {
                        state -> state.copy(

                    selectedRepeate = event.repeat.name
                )}
//            }
        }
        is DetailsEvent.SelectTime ->{


//            if (_viewState.value.selectedDay!= null){
//                _viewState.update {
//                        state -> state.copy(
//
//                    selectedTime  = null
//                )}
//            }else {
                _viewState.update {
                        state -> state.copy(

                    selectedTime = event.time
                )}
//            }



        }
        else ->{

        }
    }
}

}

sealed class DetailsEvent{
    class  SelectDay(var day:Map<String ,String>) :DetailsEvent()
    class  SelectRepeat(var repeat:ServiceType):DetailsEvent()

    class  SelectTime(var time:String):DetailsEvent()


}