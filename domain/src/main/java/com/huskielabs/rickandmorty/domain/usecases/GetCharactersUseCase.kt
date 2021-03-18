package com.huskielabs.rickandmorty.domain.usecases

import com.huskielabs.rickandmorty.domain.datasources.CharactersDataSource
import com.huskielabs.rickandmorty.domain.models.CharacterModel
import com.huskielabs.rickandmorty.domain.models.PageModel
import com.huskielabs.rickandmorty.domain.usecases.shared.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersDataSource: CharactersDataSource,
) : FlowUseCase<GetCharactersUseCase.Params, PageModel<CharacterModel>> {

    override fun invoke(params: Params): Flow<PageModel<CharacterModel>> {
        return charactersDataSource.getCharacters(params.page)
    }

    data class Params(val page: Int)

}