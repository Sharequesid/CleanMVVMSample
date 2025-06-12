package com.example.cleanmvvmsample.repository

import com.example.cleanmvvmsample.model.PokedexResponse
import com.example.cleanmvvmsample.utils.NetworkResult

interface UseCaseRepository {

    suspend fun getPokemon() : NetworkResult<PokedexResponse>
}