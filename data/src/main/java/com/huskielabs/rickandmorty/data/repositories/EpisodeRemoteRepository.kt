package com.huskielabs.rickandmorty.data.repositories

import com.huskielabs.rickandmorty.data.remote.dtos.EpisodeDTO

interface EpisodeRemoteRepository {

    suspend fun getEpisodes(): List<EpisodeDTO>

}