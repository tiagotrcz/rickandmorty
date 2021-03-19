package com.huskielabs.rickandmorty.ui.screens.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huskielabs.rickandmorty.domain.usecases.GetCharactersUseCase
import com.huskielabs.rickandmorty.shared.DispatchersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val dispatchersProvider: DispatchersProvider,
) : ViewModel(), CharacterViewModelContract {

    private var currentPage = 0
    private var nextPage = 1
    private var isLastPage = false

    init {
        getCharacters()
    }

    override val characterList = MutableStateFlow<List<CharacterViewData>>(emptyList())

    override val isLoading = MutableStateFlow(false)

    override fun getCharacters() {
        viewModelScope.launch(dispatchersProvider.ui) {
            isLoading.value = true
            delay(2000)
            try {
                currentPage = nextPage
                val result = getCharactersUseCase(GetCharactersUseCase.Params(currentPage))

                isLastPage = result.next == null
                if (!isLastPage) nextPage++

                val charactersList = result.result.map { character ->
                    CharacterViewData(
                        id = character.id,
                        image = character.image,
                        name = character.name,
                        status = character.status,
                    )
                }

                characterList.value = characterList.value + charactersList
                isLoading.value = false
            } catch (e: Exception) {
                Log.e(TAG, "getCharacters: ${e.message}", e)
            }
        }
    }

}

private const val TAG = "CharacterViewModel"