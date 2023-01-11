package com.santukis.viewmodels.home

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.home.entities.HomeState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getNowPlayingMovies: UseCase<Unit, Flow<List<Movie>>>,
    private val getUpcomingMovies: UseCase<Unit, Flow<List<Movie>>>,
    private val getPopularMovies: UseCase<Unit, Flow<List<Movie>>>
): ViewModel() {

    private val _homeState: CMutableStateFlow<HomeState> =
        MutableStateFlow(HomeState()).cMutableStateFlow()

    val homeState: CStateFlow<HomeState> = _homeState.cStateFlow()

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
                _homeState.value = _homeState.value.copy(errorMessage = error.message)
            }
            .collect { movies ->
                _homeState.value = _homeState.value.copy(nowPlayingMovies = movies)
            }
    }

    private suspend fun loadUpcoming() {
        getUpcomingMovies(Unit)
            .flowOn(Dispatchers.Default)
            .catch { error ->
                _homeState.value = _homeState.value.copy(errorMessage = error.message)
            }
            .collect { movies ->
                _homeState.value = _homeState.value.copy(upcomingMovies = movies)
            }
    }

    private suspend fun loadPopularMovies() {
        getPopularMovies(Unit)
            .flowOn(Dispatchers.Default)
            .catch { error ->
                _homeState.value = _homeState.value.copy(errorMessage = error.message)
            }
            .collect { movies ->
                _homeState.value = _homeState.value.copy(popularMovies = movies)
            }
    }
}