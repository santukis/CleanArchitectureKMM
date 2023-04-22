package com.santukis.viewmodels.movies

import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.Video
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.movies.entities.MovieDetailState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.flow.CStateFlow
import dev.icerock.moko.mvvm.flow.cMutableStateFlow
import dev.icerock.moko.mvvm.flow.cStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DefaultMovieDetailViewModel(
    private val getMovieDetail: UseCase<String, Flow<Movie>>,
    private val getMovieVideos: UseCase<String, Flow<List<Video>>>
): ViewModel(), MovieDetailViewModel {

    private val _movieDetailState: CMutableStateFlow<MovieDetailState> =
        MutableStateFlow(MovieDetailState()).cMutableStateFlow()

    override val movieDetailState: CStateFlow<MovieDetailState> = _movieDetailState.cStateFlow()

    override fun loadMovie(movieId: String) {
        loadMovieDetail(movieId)
        loadMovieVideos(movieId)
    }

    private fun loadMovieDetail(movieId: String) {
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

    private fun loadMovieVideos(movieId: String) {
        viewModelScope.launch(Dispatchers.Main) {
            getMovieVideos(movieId)
                .flowOn(Dispatchers.Default)
                .catch { error ->
                    _movieDetailState.value = _movieDetailState.value.copy(errorMessage = error.message.orEmpty())
                }
                .collect { videos ->
                    _movieDetailState.value = _movieDetailState.value.copy(videos = videos)
                }
        }
    }
}