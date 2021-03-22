package com.huskielabs.rickandmorty

sealed class Screen(val route: String) {

    object CharacterFilter : Screen("characterFilter")

}