package com.huskielabs.rickandmorty.domain.datasources

import com.huskielabs.rickandmorty.domain.models.EpisodeModel

interface EpisodeDataSource {

    suspend fun getEpisodes(): List<EpisodeModel>

}