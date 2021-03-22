package com.huskielabs.rickandmorty.data.remote

import com.huskielabs.rickandmorty.data.remote.dtos.EpisodeDTO
import com.huskielabs.rickandmorty.data.remote.service.EpisodeService
import com.huskielabs.rickandmorty.data.repositories.EpisodeRemoteRepository
import javax.inject.Inject

class EpisodeRemoteRepositoryImpl @Inject constructor(
    private val episodeService: EpisodeService,
) : EpisodeRemoteRepository {

    override suspend fun getEpisodes(): List<EpisodeDTO> {
        val episodes = mutableListOf<EpisodeDTO>()
        var isLastPage = false
        var page = 1

        while (!isLastPage) {
            val result = episodeService.getEpisodes(page)

            isLastPage = when (result.info.next == null) {
                true -> true
                false -> {
                    page++
                    false
                }
            }

            episodes.addAll(result.result)
        }

        return episodes.toList()
    }

}