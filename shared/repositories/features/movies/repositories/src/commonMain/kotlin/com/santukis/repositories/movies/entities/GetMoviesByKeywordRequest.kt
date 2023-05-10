package com.santukis.repositories.movies.entities

import com.santukis.entities.movies.Keyword

data class GetMoviesByKeywordRequest(
    val keywords: List<Keyword>,
    val page: Int = 1
)
