package com.huskielabs.rickandmorty.data.remote.dtos


import com.squareup.moshi.Json

data class EpisodeDTO(
    @Json(name = "id")
    val id: Int,
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "characters")
    val characters: List<String>,
    @Json(name = "created")
    val created: String,
    @Json(name = "episode")
    val episode: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String,
)