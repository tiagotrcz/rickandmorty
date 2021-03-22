package com.huskielabs.rickandmorty.data.remote.service

import com.huskielabs.rickandmorty.data.remote.dtos.LocationDTO
import com.huskielabs.rickandmorty.data.remote.dtos.PageDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    @GET("location")
    suspend fun getLocations(@Query("page") page: Int): PageDTO<LocationDTO>

}