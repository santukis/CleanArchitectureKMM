package com.santukis.viewmodels.moviedetail

import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import com.santukis.viewmodels.moviedetail.entities.MovieDetailState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultMovieDetailViewModel(
    private val loadMovieDetailStrategy: ViewModelStrategy<String, MovieDetailState>,
    private val loadMovieVideosStrategy: ViewModelStrategy<String, MovieDetailState>
):
    ViewModel(),
    MovieDetailViewModel {

    private val _movieDetailState: CMutableStateFlow<MovieDetailState> =
        MutableStateFlow(MovieDetailState()).cMutableStateFlow()

    override val movieDetailState: CStateFlow<MovieDetailState> = _movieDetailState.cStateFlow()

    override fun loadMovie(movieId: String) {
        loadMovieDetail(movieId)
        loadMovieVideos(movieId)
    }

    private fun loadMovieDetail(movieId: String) {
        loadMovieDetailStrategy.execute(
            viewModel = this,
            input = movieId,
            output = _movieDetailState
        )
    }

    private fun loadMovieVideos(movieId: String) {
        loadMovieVideosStrategy.execute(
            viewModel = this,
            input = movieId,
            output = _movieDetailState
        )
    }
}