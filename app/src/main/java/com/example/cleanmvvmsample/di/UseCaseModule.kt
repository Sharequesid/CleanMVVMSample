package com.example.cleanmvvmsample.di

import com.example.cleanmvvmsample.repository.UseCaseRepository
import com.example.cleanmvvmsample.repository.UseCaseRepositoryImpl
import com.example.cleanmvvmsample.usecase.GetAllPokemonUseCase
import com.example.cleanmvvmsample.usecase.GetAllPokemonUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {


    @Binds
    abstract fun getUseCase(getAllPokemonUseCaseImpl: GetAllPokemonUseCaseImpl): GetAllPokemonUseCase


    @Binds
    abstract fun provideMyRepository(useCaseRepositoryImpl: UseCaseRepositoryImpl): UseCaseRepository

}