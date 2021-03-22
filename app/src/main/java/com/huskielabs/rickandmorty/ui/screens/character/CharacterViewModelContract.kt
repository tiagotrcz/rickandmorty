package com.huskielabs.rickandmorty.ui.screens.character

import kotlinx.coroutines.flow.StateFlow

interface CharacterViewModelContract {

    val characterList: StateFlow<List<CharacterViewData>>
    val isLoading: StateFlow<Boolean>

    fun getCharacters()
    fun openFilterScreen()

}