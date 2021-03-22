package com.huskielabs.rickandmorty.domain.models

data class EpisodeModel(
    val id: Int,
    val airDate: String,
    val characters: List<String>,
    val created: String,
    val episode: String,
    val name: String,
    val url: String,
)