package com.huskielabs.rickandmorty.data.remote

import com.huskielabs.rickandmorty.data.remote.dtos.CharacterDTO
import com.huskielabs.rickandmorty.data.remote.dtos.PageDTO
import com.huskielabs.rickandmorty.data.remote.service.CharacterService
import com.huskielabs.rickandmorty.data.repositories.CharacterRemoteRepository
import javax.inject.Inject

class CharacterRemoteRepositoryImpl @Inject constructor(
    private val characterService: CharacterService,
) : CharacterRemoteRepository {

    override suspend fun getCharacters(page: Int, status: String?, gender: String?): PageDTO<CharacterDTO> {
        return characterService.getCharacters(page, status, gender)
    }

}