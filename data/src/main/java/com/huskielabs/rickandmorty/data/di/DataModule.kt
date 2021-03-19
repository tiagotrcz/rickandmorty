package com.huskielabs.rickandmorty.data.di

import com.huskielabs.rickandmorty.data.CharacterDataSourceImpl
import com.huskielabs.rickandmorty.data.remote.CharacterRemoteRepositoryImpl
import com.huskielabs.rickandmorty.data.remote.service.CharacterService
import com.huskielabs.rickandmorty.data.repositories.CharacterRemoteRepository
import com.huskielabs.rickandmorty.domain.datasources.CharacterDataSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    private const val CONNECT_TIMEOUT = 10000L
    private const val WRITE_TIMEOUT = 10000L
    private const val READ_TIMEOUT = 30000L
    private const val BASE_URL = "https://rickandmortyapi.com/api"

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): Interceptor {
        val logger = HttpLoggingInterceptor.Logger { message ->
            Platform.get().log(message, Platform.INFO, null)
        }
        return HttpLoggingInterceptor(logger)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesCharacterService(retrofit: Retrofit): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }

    @Provides
    @Singleton
    fun providesCharacterRemoteRepository(characterService: CharacterService): CharacterRemoteRepository {
        return CharacterRemoteRepositoryImpl(characterService)
    }

    @Provides
    @Singleton
    fun providersCharacterDataSource(
        characterRemoteRepository: CharacterRemoteRepository
    ): CharacterDataSource {
        return CharacterDataSourceImpl(characterRemoteRepository)
    }

}