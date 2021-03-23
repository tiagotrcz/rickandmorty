package com.huskielabs.rickandmorty.domain.usecases

import com.huskielabs.rickandmorty.domain.datasources.CharacterDataSource
import com.huskielabs.rickandmorty.domain.models.CharacterModel
import com.huskielabs.rickandmorty.domain.models.PageModel
import com.huskielabs.rickandmorty.domain.usecases.shared.UseCase
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterDataSource: CharacterDataSource,
) : UseCase<GetCharactersUseCase.Params, PageModel<CharacterModel>> {

    override suspend fun invoke(params: Params): PageModel<CharacterModel> {
        return characterDataSource.getCharacters(params.page, params.status, params.gender)
    }

    data class Params(val page: Int, val status: String?, val gender: String?)

}