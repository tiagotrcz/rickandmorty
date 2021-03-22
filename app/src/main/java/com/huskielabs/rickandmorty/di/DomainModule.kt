package com.huskielabs.rickandmorty.di

import com.huskielabs.rickandmorty.domain.datasources.CharacterDataSource
import com.huskielabs.rickandmorty.domain.datasources.EpisodeDataSource
import com.huskielabs.rickandmorty.domain.datasources.LocationDataSource
import com.huskielabs.rickandmorty.domain.usecases.GetCharactersUseCase
import com.huskielabs.rickandmorty.domain.usecases.GetEpisodesUseCase
import com.huskielabs.rickandmorty.domain.usecases.GetLocationsUseCase
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

    @Provides
    @Singleton
    fun providesGetEpisodesUseCase(
        episodeDataSource: EpisodeDataSource
    ): GetEpisodesUseCase {
        return GetEpisodesUseCase(episodeDataSource)
    }

    @Provides
    @Singleton
    fun providesGetLocationsUseCase(
        locationDataSource: LocationDataSource
    ): GetLocationsUseCase {
        return GetLocationsUseCase(locationDataSource)
    }

}