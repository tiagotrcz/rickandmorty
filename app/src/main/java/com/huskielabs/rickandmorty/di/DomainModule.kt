package com.huskielabs.rickandmorty.di

import com.huskielabs.rickandmorty.domain.datasources.CharacterDataSource
import com.huskielabs.rickandmorty.domain.usecases.GetCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun providesGetCharactersUseCase(
        characterDataSource: CharacterDataSource
    ): GetCharactersUseCase {
        return GetCharactersUseCase(characterDataSource)
    }

}