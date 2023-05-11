package com.santukis.datasources.movies.remote.entities

data class GetMoviesRequestDto(
    val language: String,
    val region: String,
    val page: String
)
