package com.huskielabs.rickandmorty.di

import com.huskielabs.rickandmorty.shared.AppDispatchersProvider
import com.huskielabs.rickandmorty.shared.DispatchersProvider
import com.huskielabs.rickandmorty.shared.Navigator
import com.huskielabs.rickandmorty.shared.NavigatorImpl
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
  fun provideNavigator(dispatchersProvider: DispatchersProvider): Navigator {
    return NavigatorImpl(dispatchersProvider)
  }

  @Provides
  @Singleton
  fun provideDispatchers(): DispatchersProvider = AppDispatchersProvider()

}