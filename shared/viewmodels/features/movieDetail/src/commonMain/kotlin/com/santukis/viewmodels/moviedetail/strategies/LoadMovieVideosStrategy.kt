package com.santukis.viewmodels.moviedetail.strategies

import com.santukis.entities.movies.Video
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.core.executeUseCase
import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import com.santukis.viewmodels.moviedetail.entities.MovieDetailState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow

class LoadMovieVideosStrategy(
    private val getMovieVideos: UseCase<String, Flow<List<Video>>>
): ViewModelStrategy<String, MovieDetailState> {

    override fun execute(
        viewModel: ViewModel,
        input: String,
        output: CMutableStateFlow<MovieDetailState>
    ) {
        viewModel.executeUseCase(
            useCase = getMovieVideos,
            request = input
        ) { videos ->
            output.value = output.value
                .copy(videos = videos)
        }
    }
}