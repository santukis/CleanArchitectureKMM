package com.santukis.viewmodels.home.entities

import com.santukis.entities.movies.Movie
import com.santukis.viewmodels.core.entities.MovieSection

data class HomeState(
    val nowPlayingMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val couldLikeMovies: List<Movie> = emptyList(),
    val errorMessage: String? = null
) {

    fun shouldUpdateData(): Boolean =
        nowPlayingMovies.isEmpty()
                || upcomingMovies.isEmpty()
                || popularMovies.isEmpty()

    fun getSections(): List<Pair<MovieSection, List<Movie>>> =
        listOf(
            MovieSection.UpcomingMovies to upcomingMovies,
            MovieSection.PopularMovies to popularMovies,
            MovieSection.CouldYouLikeMovies to couldLikeMovies
        )
}
