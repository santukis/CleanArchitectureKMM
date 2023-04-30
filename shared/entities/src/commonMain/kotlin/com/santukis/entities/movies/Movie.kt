package com.santukis.entities.movies

data class Movie(
    val id: Int,
    val imdbId: String,
    val images: Images,
    val titles: Titles,
    val rating: Rating,
    val overview: String
)
