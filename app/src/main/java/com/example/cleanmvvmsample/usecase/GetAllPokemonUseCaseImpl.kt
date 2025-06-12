package com.example.cleanmvvmsample.usecase

import com.example.cleanmvvmsample.model.PokedexResponse
import com.example.cleanmvvmsample.repository.UseCaseRepository
import com.example.cleanmvvmsample.utils.NetworkResult
import javax.inject.Inject

/**
 * Search use case implementation
 *
 * It will take search repository object , calls search image method on repository and return result to calling co-routine
 */
class GetAllPokemonUseCaseImpl @Inject constructor(
    private val useCaseRepository: UseCaseRepository
) : GetAllPokemonUseCase {
    override suspend operator fun invoke(): NetworkResult<PokedexResponse> {
        return useCaseRepository.getPokemon()
    }
}