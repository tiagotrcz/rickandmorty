package com.huskielabs.rickandmorty.ui.screens.episode

import kotlinx.coroutines.flow.StateFlow

interface EpisodeViewModelContract {

    val episodeList: StateFlow<Map<String, List<EpisodeViewData>>>
    val isLoading: StateFlow<Boolean>

    fun getEpisodes()

}