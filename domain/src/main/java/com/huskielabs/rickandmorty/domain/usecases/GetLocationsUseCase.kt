package com.huskielabs.rickandmorty.domain.usecases

import com.huskielabs.rickandmorty.domain.datasources.LocationDataSource
import com.huskielabs.rickandmorty.domain.models.LocationModel
import com.huskielabs.rickandmorty.domain.models.PageModel
import com.huskielabs.rickandmorty.domain.usecases.shared.UseCase
import javax.inject.Inject

class GetLocationsUseCase @Inject constructor(
    private val locationDataSource: LocationDataSource,
) : UseCase<GetLocationsUseCase.Params, PageModel<LocationModel>> {

    override suspend fun invoke(params: Params): PageModel<LocationModel> {
        return locationDataSource.getLocations(page = params.page)
    }

    data class Params(val page: Int)

}