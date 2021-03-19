package com.huskielabs.rickandmorty.domain.datasources

import com.huskielabs.rickandmorty.domain.models.CharacterModel
import com.huskielabs.rickandmorty.domain.models.PageModel

interface CharacterDataSource {

    suspend fun getCharacters(page: Int): PageModel<CharacterModel>

}