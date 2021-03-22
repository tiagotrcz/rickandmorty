package com.huskielabs.rickandmorty.data.remote.dtos

import com.squareup.moshi.Json

data class PageDTO<T>(
    @Json(name = "info")
    val pageInfo: Info,
    @Json(name = "results")
    val result: List<T>,
) {
    data class Info(
        @Json(name = "next")
        val nextPage: String?,
    )
}