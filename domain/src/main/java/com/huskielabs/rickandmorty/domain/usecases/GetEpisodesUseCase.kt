package com.huskielabs.rickandmorty.domain.usecases

import com.huskielabs.rickandmorty.domain.datasources.EpisodeDataSource
import com.huskielabs.rickandmorty.domain.models.EpisodeModel
import com.huskielabs.rickandmorty.domain.usecases.shared.NoParams
import com.huskielabs.rickandmorty.domain.usecases.shared.UseCase
import javax.inject.Inject

class GetEpisodesUseCase @Inject constructor(
    private val episodeDataSource: EpisodeDataSource,
) : UseCase<NoParams, Map<String, List<EpisodeModel>>> {

    override suspend fun invoke(params: NoParams): Map<String, List<EpisodeModel>> {
        return episodeDataSource.getEpisodes().groupBy { it.episode.substring(0, 3) }
    }

}