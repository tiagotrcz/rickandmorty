package com.huskielabs.rickandmorty.ui.screens.location

import kotlinx.coroutines.flow.StateFlow

interface LocationViewModelContract {

    val locationList: StateFlow<List<LocationViewData>>
    val isLoading: StateFlow<Boolean>

    fun getLocations()

}