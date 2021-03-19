package com.huskielabs.rickandmorty.data.remote.dtos


import com.squareup.moshi.Json

data class CharacterDTO(
    @Json(name = "created")
    val created: String,
    @Json(name = "episode")
    val episode: List<String>,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "location")
    val location: Location,
    @Json(name = "name")
    val name: String,
    @Json(name = "origin")
    val origin: Origin,
    @Json(name = "species")
    val species: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String,
) {
    data class Location(
        @Json(name = "name")
        val name: String,
        @Json(name = "url")
        val url: String,
    )

    data class Origin(
        @Json(name = "name")
        val name: String,
        @Json(name = "url")
        val url: String,
    )
}