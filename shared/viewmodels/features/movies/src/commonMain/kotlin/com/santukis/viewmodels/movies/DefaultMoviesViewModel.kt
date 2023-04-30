package com.santukis.viewmodels.movies

import com.santukis.entities.movies.Movie
import com.santukis.viewmodels.core.entities.MovieSection
import com.santukis.viewmodels.core.events.OnUiEvent
import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import com.santukis.viewmodels.movies.entities.MoviesState
import com.santukis.viewmodels.movies.events.OnSectionEndReached
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultMoviesViewModel(
    private val loadUpcomingMoviesStrategy: ViewModelStrategy<Unit, List<Movie>>,
    private val loadPopularMoviesStrategy: ViewModelStrategy<Unit, List<Movie>>,
    private val loadCouldYouLikeMoviesStrategy: ViewModelStrategy<Unit, List<Movie>>
): MoviesViewModel() {

    private val _moviesState: CMutableStateFlow<MoviesState> =
        MutableStateFlow(MoviesState()).cMutableStateFlow()

    override val moviesState: CStateFlow<MoviesState> = _moviesState.cStateFlow()

    override fun onUiEvent(event: OnUiEvent) {
        when(event) {
            is OnSectionEndReached -> loadSectionMovies(event.section)
            else -> {}
        }
    }

    override fun loadSectionMovies(section: MovieSection) {
        when(section) {
            MovieSection.UpcomingMovies -> loadUpcomingMovies()
            MovieSection.PopularMovies -> loadPopularMovies()
            MovieSection.CouldYouLikeMovies -> loadCouldYouLikeMovies()
            else -> {}
        }
    }

    private fun loadUpcomingMovies() {
        loadUpcomingMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            onSuccess = { movies ->
                _moviesState.value = _moviesState.value.addMovies(movies)
            }
        )
    }

    private fun loadPopularMovies() {
        loadPopularMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            onSuccess = { movies ->
                _moviesState.value = _moviesState.value.addMovies(movies)
            }
        )
    }

    private fun loadCouldYouLikeMovies() {
        loadCouldYouLikeMoviesStrategy.execute(
            viewModel = this,
            input = Unit,
            onSuccess = { movies ->
                _moviesState.value = _moviesState.value.addMovies(movies)
            }
        )
    }
}