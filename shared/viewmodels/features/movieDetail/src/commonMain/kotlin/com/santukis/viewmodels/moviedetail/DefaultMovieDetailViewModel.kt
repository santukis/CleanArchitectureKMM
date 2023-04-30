package com.santukis.viewmodels.moviedetail

import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.Video
import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import com.santukis.viewmodels.moviedetail.entities.MovieDetailState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import kotlinx.coroutines.flow.MutableStateFlow

class DefaultMovieDetailViewModel(
    private val loadMovieDetailStrategy: ViewModelStrategy<String, Movie>,
    private val loadMovieVideosStrategy: ViewModelStrategy<String, List<Video>>
):
    MovieDetailViewModel() {

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
            onSuccess = { movieDetail ->
                _movieDetailState.value = _movieDetailState.value.copy(movie = movieDetail)
            }
        )
    }

    private fun loadMovieVideos(movieId: String) {
        loadMovieVideosStrategy.execute(
            viewModel = this,
            input = movieId,
            onSuccess = { videos ->
                _movieDetailState.value = _movieDetailState.value.copy(videos = videos)
            }
        )
    }
}