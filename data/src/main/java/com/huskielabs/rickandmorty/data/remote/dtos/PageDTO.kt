package com.huskielabs.rickandmorty.data.remote.dtos

import com.squareup.moshi.Json

data class PageDTO<T>(
    val info: Info,
    val result: List<T>,
) {
    data class Info(
        @Json(name = "next")
        val next: String?,
    )
}