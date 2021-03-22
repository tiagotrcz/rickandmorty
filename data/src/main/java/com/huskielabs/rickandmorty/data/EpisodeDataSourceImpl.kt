package com.huskielabs.rickandmorty.data

import com.huskielabs.rickandmorty.data.repositories.EpisodeRemoteRepository
import com.huskielabs.rickandmorty.domain.datasources.EpisodeDataSource
import com.huskielabs.rickandmorty.domain.models.EpisodeModel
import javax.inject.Inject

class EpisodeDataSourceImpl @Inject constructor(
    private val episodeRemoteRepository: EpisodeRemoteRepository,
) : EpisodeDataSource {

    override suspend fun getEpisodes(): List<EpisodeModel> {
        return episodeRemoteRepository.getEpisodes().map { episode ->
            EpisodeModel(
                id = episode.id,
                airDate = episode.airDate,
                characters = episode.characters,
                created = episode.created,
                episode = episode.episode,
                name = episode.name,
                url = episode.url,
            )
        }
    }

}