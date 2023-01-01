package com.santukis.viewmodels.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.movies.entities.MovieDetailState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMovieDetail: UseCase<String, Flow<Movie>>
): ViewModel() {

    private val _movieDetailState: CMutableStateFlow<MovieDetailState> =
        MutableStateFlow(MovieDetailState()).cMutableStateFlow()

    val movieDetailState: CStateFlow<MovieDetailState> = _movieDetailState.cStateFlow()

    fun loadMovie(movieId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            getMovieDetail(movieId)
                .flowOn(Dispatchers.Default)
                .catch { error ->
                    _movieDetailState.value = _movieDetailState.value.copy(errorMessage = error.message.orEmpty())
                }
                .collect { movie ->
                    _movieDetailState.value = _movieDetailState.value.copy(movie = movie)
                }
        }
    }
}