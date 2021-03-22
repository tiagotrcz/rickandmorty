package com.huskielabs.rickandmorty.data.repositories

import com.huskielabs.rickandmorty.data.remote.dtos.LocationDTO
import com.huskielabs.rickandmorty.data.remote.dtos.PageDTO

interface LocationRemoteRepository {

    suspend fun getLocations(page: Int): PageDTO<LocationDTO>

}