package com.santukis.viewmodels.home

import com.santukis.entities.movies.Movie
import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import com.santukis.viewmodels.home.entities.HomeState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultHomeViewModel(
    private val loadNowPlayingMoviesStrategy: ViewModelStrategy<Unit, List<Movie>>,
    private val loadUpcomingMoviesStrategy: ViewModelStrategy<Unit,  List<Movie>>,
    private val loadPopularMoviesStrategy: ViewModelStrategy<Unit,  List<Movie>>,
    private val loadCouldLikeMoviesStrategy: ViewModelStrategy<Unit,  List<Movie>>,
):
    HomeViewModel() {

    private val _homeState: CMutableStateFlow<HomeState> =
        MutableStateFlow(HomeState()).cMutableStateFlow()

    override val homeState: CStateFlow<HomeState> = _homeState.cStateFlow()

    override fun loadHomeData() {
        loadNowPlayingMovies()
        loadUpcoming()
        loadPopularMovies()
        loadMoviesByMostFrequentlyKeyword()
    }

    private fun loadNowPlayingMovies() {
        loadNowPlayingMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            onSuccess = { movies ->
                _homeState.value = _homeState.value.copy(
                    nowPlayingMovies = movies
                )
            }
        )
    }

    private fun loadUpcoming() {
        loadUpcomingMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            onSuccess = { movies ->
                _homeState.value = _homeState.value.copy(
                    upcomingMovies = movies
                )
            }
        )
    }

    private fun loadPopularMovies() {
        loadPopularMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            onSuccess = { movies ->
                _homeState.value = _homeState.value.copy(
                    popularMovies = movies
                )
            }
        )
    }

    private fun loadMoviesByMostFrequentlyKeyword() {
        loadCouldLikeMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            onSuccess = { movies ->
                _homeState.value = _homeState.value.copy(
                    couldLikeMovies = movies
                )
            }
        )
    }
}

