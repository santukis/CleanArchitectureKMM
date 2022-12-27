package com.santukis.viewmodels.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.movies.entities.MovieDetailState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMovieDetail: UseCase<String, Flow<Movie>>
) {

    private val movieDetailState: MutableStateFlow<MovieDetailState> =
        MutableStateFlow(MovieDetailState())

    fun observeMovieDetail(): StateFlow<MovieDetailState> = movieDetailState

    fun loadMovie(movieId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            getMovieDetail(movieId)
                .flowOn(Dispatchers.Default)
                .catch { error ->
                    movieDetailState.value = movieDetailState.value.copy(errorMessage = error.message.orEmpty())
                }
                .collect { movie ->
                    movieDetailState.value = movieDetailState.value.copy(movie = movie)
                }
        }
    }
}