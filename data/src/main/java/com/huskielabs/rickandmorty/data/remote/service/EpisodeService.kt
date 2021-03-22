package com.huskielabs.rickandmorty.data.remote.service

import com.huskielabs.rickandmorty.data.remote.dtos.EpisodeDTO
import com.huskielabs.rickandmorty.data.remote.dtos.PageDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodeService {

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int): PageDTO<EpisodeDTO>

}