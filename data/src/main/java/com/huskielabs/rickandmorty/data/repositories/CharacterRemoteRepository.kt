package com.huskielabs.rickandmorty.data.repositories

import com.huskielabs.rickandmorty.data.remote.dtos.CharacterDTO
import com.huskielabs.rickandmorty.data.remote.dtos.PageDTO

interface CharacterRemoteRepository {

    suspend fun getCharacters(page: Int): PageDTO<CharacterDTO>

}