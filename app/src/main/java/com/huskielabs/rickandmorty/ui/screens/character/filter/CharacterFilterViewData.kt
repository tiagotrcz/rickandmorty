package com.huskielabs.rickandmorty.ui.screens.character.filter

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class CharacterFilterViewData(
    val status: String,
    val gender: String,
) : Parcelable
