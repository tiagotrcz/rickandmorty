package com.huskielabs.rickandmorty.domain.models

data class PageModel<T> (
    val nextPage: String?,
    val result: List<T>,
)