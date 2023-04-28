package com.santukis.viewmodels.home

import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import com.santukis.viewmodels.home.entities.MoviesState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultMoviesViewModel(
    private val loadNowPlayingMoviesStrategy: ViewModelStrategy<Unit, MoviesState>,
    private val loadUpcomingMoviesStrategy: ViewModelStrategy<Unit, MoviesState>,
    private val loadPopularMoviesStrategy: ViewModelStrategy<Unit, MoviesState>,
    private val loadCouldLikeMoviesStrategy: ViewModelStrategy<Unit, MoviesState>,
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
        loadNowPlayingMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            output = _moviesState
        )
    }

    private fun loadUpcoming() {
        loadUpcomingMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            output = _moviesState
        )
    }

    private fun loadPopularMovies() {
        loadPopularMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            output = _moviesState
        )
    }

    private fun loadMoviesByMostFrequentlyKeyword() {
        loadCouldLikeMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            output = _moviesState
        )
    }
}

