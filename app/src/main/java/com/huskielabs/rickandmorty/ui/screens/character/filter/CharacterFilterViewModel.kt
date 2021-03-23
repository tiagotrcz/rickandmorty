package com.huskielabs.rickandmorty.ui.screens.character.filter

import androidx.lifecycle.ViewModel
import com.huskielabs.rickandmorty.shared.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterFilterViewModel @Inject constructor(
    private val navigator: Navigator,
) : ViewModel(), CharacterFilterViewModelContract {

    override fun applyFilter(characterFilter: CharacterFilterViewData) {
        with(navigator) {
            sendArgumentToBackStackEntry(ARG_CHARACTER_FILTER to characterFilter)
            pop()
        }
    }
}

const val ARG_CHARACTER_FILTER = "argCharacterFilter"