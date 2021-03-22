package com.huskielabs.rickandmorty.domain.datasources

import com.huskielabs.rickandmorty.domain.models.LocationModel
import com.huskielabs.rickandmorty.domain.models.PageModel

interface LocationDataSource {

    suspend fun getLocations(page: Int): PageModel<LocationModel>

}