package com.santukis.viewmodels.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.movies.entities.MoviesState
import com.santukis.viewmodels.movies.entities.MovieDetailState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMovieDetail: UseCase<String, Flow<Movie>>,
    private val getNowPlayingMovies: UseCase<Unit, Flow<List<Movie>>>
): ViewModel() {

    private val _movieDetailState: CMutableStateFlow<MovieDetailState> =
        MutableStateFlow(MovieDetailState()).cMutableStateFlow()

    private val _nowPlayingMoviesState: CMutableStateFlow<MoviesState> =
        MutableStateFlow(MoviesState()).cMutableStateFlow()

    val movieDetailState: CStateFlow<MovieDetailState> = _movieDetailState.cStateFlow()
    val nowPlayingMoviesState: CStateFlow<MoviesState> = _nowPlayingMoviesState.cStateFlow()

    fun loadMovie(movieId: String) {
        viewModelScope.launch(Dispatchers.Main) {
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

    fun loadNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            getNowPlayingMovies(Unit)
                .flowOn(Dispatchers.Default)
                .catch { error ->
                    _nowPlayingMoviesState.value = _nowPlayingMoviesState.value.copy(errorMessage = error.message)
                }
                .collect { movies ->
                    _nowPlayingMoviesState.value = _nowPlayingMoviesState.value.copy(movies = movies)
                }
        }
    }
}