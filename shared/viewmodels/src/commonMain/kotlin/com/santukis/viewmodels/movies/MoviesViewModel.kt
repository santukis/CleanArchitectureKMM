package com.santukis.viewmodels.movies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.movies.entities.MoviesState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getNowPlayingMovies: UseCase<Unit, Flow<List<Movie>>>,
    private val getUpcomingMovies: UseCase<Unit, Flow<List<Movie>>>,
    private val getPopularMovies: UseCase<Unit, Flow<List<Movie>>>
): ViewModel() {

    private val _moviesState: CMutableStateFlow<MoviesState> =
        MutableStateFlow(MoviesState()).cMutableStateFlow()

    val moviesState: CStateFlow<MoviesState> = _moviesState.cStateFlow()

    fun loadHomeData() {
        viewModelScope.launch(Dispatchers.Main) {
            loadNowPlayingMovies()
            loadUpcoming()
            loadPopularMovies()
        }
    }

    private suspend fun loadNowPlayingMovies() {
        getNowPlayingMovies(Unit)
            .flowOn(Dispatchers.Default)
            .catch { error ->
                _moviesState.value = _moviesState.value.copy(errorMessage = error.message)
            }
            .collect { movies ->
                _moviesState.value = _moviesState.value.copy(nowPlayingMovies = movies)
            }
    }

    private suspend fun loadUpcoming() {
        getUpcomingMovies(Unit)
            .flowOn(Dispatchers.Default)
            .catch { error ->
                _moviesState.value = _moviesState.value.copy(errorMessage = error.message)
            }
            .collect { movies ->
                _moviesState.value = _moviesState.value.copy(upcomingMovies = movies)
            }
    }

    private suspend fun loadPopularMovies() {
        getPopularMovies(Unit)
            .flowOn(Dispatchers.Default)
            .catch { error ->
                _moviesState.value = _moviesState.value.copy(errorMessage = error.message)
            }
            .collect { movies ->
                _moviesState.value = _moviesState.value.copy(popularMovies = movies)
            }
    }
}