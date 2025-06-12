package com.example.cleanmvvmsample.usecase

import com.example.cleanmvvmsample.model.PokedexResponse
import com.example.cleanmvvmsample.utils.NetworkResult

interface GetAllPokemonUseCase {

    suspend operator fun invoke(): NetworkResult<PokedexResponse>

}