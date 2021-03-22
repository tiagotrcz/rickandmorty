package com.huskielabs.rickandmorty.ui.screens.episode

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huskielabs.rickandmorty.domain.usecases.GetEpisodesUseCase
import com.huskielabs.rickandmorty.domain.usecases.shared.NoParams
import com.huskielabs.rickandmorty.shared.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val getEpisodesUseCase: GetEpisodesUseCase,
    private val dispatchersProvider: DispatchersProvider,
) : ViewModel(), EpisodeViewModelContract {

    override val episodeList = MutableStateFlow<Map<String, List<EpisodeViewData>>>(mapOf())
    override val isLoading = MutableStateFlow(false)

    init {
        getEpisodes()
    }

    override fun getEpisodes() {
        viewModelScope.launch(dispatchersProvider.io) {
            isLoading.value = true

            try {
                val result = getEpisodesUseCase(NoParams)

                isLoading.value = false
                episodeList.value = result.mapValues {
                    it.value.map { episode ->
                        EpisodeViewData(
                            episode.id,
                            episode.name,
                            episode.episode,
                            episode.airDate,
                        )
                    }
                }
            } catch (e: Exception) {
                isLoading.value = false
                Log.e(TAG, "getEpisodes: ${e.message}", e)
            }
        }
    }

    companion object {
        private const val TAG = "EpisodeViewModel"
    }
}