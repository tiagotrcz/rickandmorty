package com.huskielabs.rickandmorty.ui.screens.location

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huskielabs.rickandmorty.domain.usecases.GetLocationsUseCase
import com.huskielabs.rickandmorty.shared.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val dispatchersProvider: DispatchersProvider,
) : ViewModel(), LocationViewModelContract {

    override val locationList = MutableStateFlow<List<LocationViewData>>(emptyList())
    override val isLoading = MutableStateFlow(false)

    private var currentPage = 0
    private var nextPage = 1
    private var isLastPage = false

    init {
        getLocations()
    }

    override fun getLocations() {
        if (isLastPage) return
        viewModelScope.launch(dispatchersProvider.io) {
            isLoading.value = true

            try {
                currentPage = nextPage
                val result = getLocationsUseCase(GetLocationsUseCase.Params(currentPage))

                isLastPage = result.nextPage == null
                if (!isLastPage) nextPage++

                val locations = result.result.map { location ->
                    LocationViewData(
                        id = location.id,
                        type = location.type,
                        name = location.name,
                    )
                }

                locationList.value = locationList.value + locations
                isLoading.value = false
            } catch (e: Exception) {
                isLoading.value = false
                Log.e(TAG, "getLocations: ${e.message}", e)
            }
        }
    }

    companion object {
        private const val TAG = "LocationViewModel"
    }
}