package com.example.cleanmvvmsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmvvmsample.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    val pokemonLiveData get() = mainRepository.pokLiveData

    fun getAllPokemon() {
        viewModelScope.launch {
            mainRepository.getPokemon()
        }
    }

}