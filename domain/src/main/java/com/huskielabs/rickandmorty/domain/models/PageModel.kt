package com.huskielabs.rickandmorty.domain.models

data class PageModel<T> (
    val next: String?,
    val result: List<T>,
)