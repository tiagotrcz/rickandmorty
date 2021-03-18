package com.huskielabs.rickandmorty.domain.datasources

import com.huskielabs.rickandmorty.domain.models.CharacterModel
import com.huskielabs.rickandmorty.domain.models.PageModel
import kotlinx.coroutines.flow.Flow

interface CharactersDataSource {

    fun getCharacters(page: Int): Flow<PageModel<CharacterModel>>

}