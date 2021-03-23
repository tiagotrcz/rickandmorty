package com.huskielabs.rickandmorty.data.remote.service

import com.huskielabs.rickandmorty.data.remote.dtos.CharacterDTO
import com.huskielabs.rickandmorty.data.remote.dtos.PageDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("status") status: String?,
        @Query("gender") gender: String?,
    ): PageDTO<CharacterDTO>

}