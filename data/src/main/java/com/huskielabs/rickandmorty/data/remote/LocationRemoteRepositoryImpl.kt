package com.huskielabs.rickandmorty.data.remote

import com.huskielabs.rickandmorty.data.remote.dtos.LocationDTO
import com.huskielabs.rickandmorty.data.remote.dtos.PageDTO
import com.huskielabs.rickandmorty.data.remote.service.LocationService
import com.huskielabs.rickandmorty.data.repositories.LocationRemoteRepository
import javax.inject.Inject

class LocationRemoteRepositoryImpl @Inject constructor(
    private val locationService: LocationService,
) : LocationRemoteRepository {

    override suspend fun getLocations(page: Int): PageDTO<LocationDTO> {
        return locationService.getLocations(page)
    }

}