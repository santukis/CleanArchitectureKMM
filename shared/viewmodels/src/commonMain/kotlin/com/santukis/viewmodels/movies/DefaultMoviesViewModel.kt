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

class DefaultMoviesViewModel(
    private val getNowPlayingMovies: UseCase<Unit, Flow<List<Movie>>>,
    private val getUpcomingMovies: UseCase<Unit, Flow<List<Movie>>>,
    private val getPopularMovies: UseCase<Unit, Flow<List<Movie>>>,
    private val getMoviesByKeyword: UseCase<Unit, Flow<List<Movie>>>
):
    ViewModel(),
    MoviesViewModel {

    private val _moviesState: CMutableStateFlow<MoviesState> =
        MutableStateFlow(MoviesState()).cMutableStateFlow()

    override val moviesState: CStateFlow<MoviesState> = _moviesState.cStateFlow()

    override fun loadHomeData() {
        loadNowPlayingMovies()
        loadUpcoming()
        loadPopularMovies()
        loadMoviesByMostFrequentlyKeyword()
    }

    private fun loadNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.Main) {
            getNowPlayingMovies(Unit)
                .flowOn(Dispatchers.Default)
                .catch { error ->
                    _moviesState.value = _moviesState.value.copy(errorMessage = error.message)
                }
                .collect { movies ->
                    _moviesState.value = _moviesState.value.copy(nowPlayingMovies = movies)
                }
        }
    }

    private fun loadUpcoming() {
        viewModelScope.launch(Dispatchers.Main) {
            getUpcomingMovies(Unit)
                .flowOn(Dispatchers.Default)
                .catch { error ->
                    _moviesState.value = _moviesState.value.copy(errorMessage = error.message)
                }
                .collect { movies ->
                    _moviesState.value = _moviesState.value.copy(upcomingMovies = movies)
                }
        }
    }

    private fun loadPopularMovies() {
        viewModelScope.launch(Dispatchers.Main) {
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

    private fun loadMoviesByMostFrequentlyKeyword() {
        viewModelScope.launch(Dispatchers.Main) {
            getMoviesByKeyword(Unit)
                .flowOn(Dispatchers.Default)
                .catch {  error ->
                    _moviesState.value = _moviesState.value.copy(errorMessage = error.message)
                }
                .collect { movies ->
                    _moviesState.value = _moviesState.value.copy(couldLikeMovies = movies)
                }
        }
    }
}

