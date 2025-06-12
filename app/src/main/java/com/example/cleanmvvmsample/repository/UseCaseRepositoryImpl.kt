package com.example.cleanmvvmsample.repository

import com.example.cleanmvvmsample.api.APIService
import com.example.cleanmvvmsample.model.PokedexResponse
import com.example.cleanmvvmsample.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class UseCaseRepositoryImpl @Inject constructor(private val apiService: APIService): UseCaseRepository {

    override suspend fun getPokemon(): NetworkResult<PokedexResponse> {

        val response = apiService.getPokemon()
        if (response.isSuccessful && response.body() != null) {
            return NetworkResult.Success(response.body()!!)
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            return NetworkResult.Error(errorObj.getString("message"))
        } else {
            return NetworkResult.Error("Something Went Wrong")
        }
    }

}