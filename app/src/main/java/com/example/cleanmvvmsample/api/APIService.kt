package com.example.cleanmvvmsample.api

import com.example.cleanmvvmsample.model.PokedexResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("pokedex/2/")
    suspend fun getPokemon(): Response<PokedexResponse>

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

}