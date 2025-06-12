package com.example.cleanmvvmsample.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanmvvmsample.model.PokedexResponse
import com.example.cleanmvvmsample.usecase.GetAllPokemonUseCase
import com.example.cleanmvvmsample.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getAllPokemonUseCase: GetAllPokemonUseCase): ViewModel() {

    private val _pokLiveData = MutableLiveData<NetworkResult<PokedexResponse>>()
    val pokLiveData get() = _pokLiveData

    fun getAllPokemon() {
        viewModelScope.launch {
            pokLiveData.value = getAllPokemonUseCase()
        }
    }

}