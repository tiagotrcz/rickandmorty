package com.huskielabs.rickandmorty.shared

val <T> List<T>.lastIndexForPagination: Int
    get() = if (this.lastIndex <= 4) this.lastIndex else this.lastIndex - 4

