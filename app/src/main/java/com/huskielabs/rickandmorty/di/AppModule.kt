package com.huskielabs.rickandmorty.di

import com.huskielabs.rickandmorty.shared.AppDispatchersProvider
import com.huskielabs.rickandmorty.shared.DispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideDispatchers(): DispatchersProvider = AppDispatchersProvider()

}