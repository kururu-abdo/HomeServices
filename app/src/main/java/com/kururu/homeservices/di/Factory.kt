package com.kururu.homeservices.di

import com.kururu.homeservices.data.repository.MainRepository
import com.kururu.homeservices.logic.DetailsViewModel
import com.kururu.homeservices.logic.MainViewModel

// Definition of a Factory interface with a function to create objects of a type
interface Factory<T> {
    fun create(): T
}

// Factory for LoginViewModel.
// Since LoginViewModel depends on UserRepository, in order to create instances of
// LoginViewModel, you need an instance of UserRepository that you pass as a parameter.
class MainViewModelFactory(private val mainRepository: MainRepository) : Factory<MainViewModel> {
    override fun create(): MainViewModel {
        return MainViewModel(mainRepository)
    }
}


class DetailsViewModelFactory(private val mainRepository: MainRepository) : Factory<DetailsViewModel> {
    override fun create(): DetailsViewModel {
        return DetailsViewModel(mainRepository)
    }
}
