package com.kururu.homeservices.logic

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kururu.homeservices.data.model.ServiceModel
import com.kururu.homeservices.data.repository.MainRepository
import com.kururu.homeservices.ui.states.ServiceState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel (var repo:MainRepository  ) :ViewModel() {



    private val _viewState = MutableStateFlow(ServiceState())
    val viewState = _viewState.asStateFlow()

init {
    _viewState.update {
        state -> state.copy(
        worders = repo.getAllWorders(),
        worker = repo.getRandomWorder()
        )
    }
}




fun toggleService( serviceModel: ServiceModel){
    viewModelScope.launch {
        if (
            _viewState.value.selectedServiceModel != null &&

            _viewState.value.selectedServiceModel == serviceModel
        ) {
            _viewState.value = _viewState.value.copy(
                selectedServiceModel = null ,
                showingFab = false
            )
            _viewState.update { currentState -> currentState.copy(selectedServiceModel = null ,

                showingFab = false
                ) }
        } else {

            _viewState.value = _viewState.value.copy(
                selectedServiceModel = serviceModel ,
                showingFab = true
            )
            _viewState.update { currentState -> currentState.copy(selectedServiceModel = serviceModel ,
            showingFab = true


                ) }

        }
    }



}
fun getAllServices()   = repo.getAllServerice()

}