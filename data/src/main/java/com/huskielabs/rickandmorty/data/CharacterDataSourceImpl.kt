package com.huskielabs.rickandmorty.data

import com.huskielabs.rickandmorty.data.repositories.CharacterRemoteRepository
import com.huskielabs.rickandmorty.domain.datasources.CharacterDataSource
import com.huskielabs.rickandmorty.domain.models.CharacterModel
import com.huskielabs.rickandmorty.domain.models.PageModel
import javax.inject.Inject

class CharacterDataSourceImpl @Inject constructor(
    private val characterRemoteRepository: CharacterRemoteRepository,
) : CharacterDataSource {

    override suspend fun getCharacters(page: Int): PageModel<CharacterModel> {
        val response = characterRemoteRepository.getCharacters(page)
        return PageModel(
            next = response.info.next,
            result = response.result.map { character ->
                CharacterModel(
                    created = character.created,
                    episode = character.episode,
                    gender = character.gender,
                    id = character.id,
                    image = character.image,
                    location = CharacterModel.Location(
                        name = character.location.name,
                        url = character.location.url,
                    ),
                    name = character.name,
                    origin = CharacterModel.Origin(
                        name = character.origin.name,
                        url = character.origin.url,
                    ),
                    species = character.species,
                    status = character.status,
                    type = character.type,
                    url = character.url,
                )
            }
        )
    }

}