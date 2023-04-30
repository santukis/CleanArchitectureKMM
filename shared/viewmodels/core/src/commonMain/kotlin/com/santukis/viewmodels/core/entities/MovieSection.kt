package com.santukis.viewmodels.core.entities

enum class MovieSection(val value: String) {
    UpcomingMovies("UpcomingMovies"),
    PopularMovies("PopularMovies"),
    TopRatedMovies("TopRatedMovies"),
    CouldYouLikeMovies("CouldYouLikeMovies");

        companion object {
            fun from(title: String): MovieSection =
                values().firstOrNull { it.value == title } ?: UpcomingMovies
        }
}
