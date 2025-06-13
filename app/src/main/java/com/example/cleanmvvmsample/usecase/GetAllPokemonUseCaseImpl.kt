package com.example.cleanmvvmsample.usecase

import com.example.cleanmvvmsample.model.PokedexResponse
import com.example.cleanmvvmsample.repository.UseCaseRepository
import com.example.cleanmvvmsample.utils.NetworkResult
import javax.inject.Inject


class GetAllPokemonUseCaseImpl @Inject constructor(
    private val useCaseRepository: UseCaseRepository
) : GetAllPokemonUseCase {
    override suspend operator fun invoke(): NetworkResult<PokedexResponse> {
        return useCaseRepository.getPokemon()
    }
}