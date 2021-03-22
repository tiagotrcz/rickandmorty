package com.huskielabs.rickandmorty.domain.models


data class LocationModel(
    val id: Int,
    val created: String,
    val dimension: String,
    val name: String,
    val residents: List<String>,
    val type: String,
    val url: String,
)