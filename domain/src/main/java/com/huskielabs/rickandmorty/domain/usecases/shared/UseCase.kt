package com.huskielabs.rickandmorty.domain.usecases.shared

interface UseCase<in Params, out Result> {
    suspend operator fun invoke(params: Params): Result
}