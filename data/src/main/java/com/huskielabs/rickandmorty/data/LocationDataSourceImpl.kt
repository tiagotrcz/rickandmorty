package com.huskielabs.rickandmorty.data

import com.huskielabs.rickandmorty.data.repositories.LocationRemoteRepository
import com.huskielabs.rickandmorty.domain.datasources.LocationDataSource
import com.huskielabs.rickandmorty.domain.models.LocationModel
import com.huskielabs.rickandmorty.domain.models.PageModel
import javax.inject.Inject

class LocationDataSourceImpl @Inject constructor(
    private val locationRemoteRepository: LocationRemoteRepository,
) : LocationDataSource {

    override suspend fun getLocations(page: Int): PageModel<LocationModel> {
        val response = locationRemoteRepository.getLocations(page)
        return PageModel(
            nextPage = response.pageInfo.nextPage,
            result = response.result.map { location ->
                LocationModel(
                    id = location.id,
                    created = location.created,
                    dimension = location.dimension,
                    name = location.name,
                    residents = location.residents,
                    type = location.type,
                    url = location.url,
                )
            }
        )
    }

}