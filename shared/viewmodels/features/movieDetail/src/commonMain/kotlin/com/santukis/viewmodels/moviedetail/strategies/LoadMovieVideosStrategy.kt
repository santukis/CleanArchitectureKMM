package com.santukis.viewmodels.moviedetail.strategies

import com.santukis.entities.movies.Video
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.core.executeUseCase
import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow

class LoadMovieVideosStrategy(
    private val getMovieVideos: UseCase<String, Flow<List<Video>>>
): ViewModelStrategy<String, List<Video>> {

    override fun execute(
        viewModel: ViewModel,
        input: String,
        onSuccess: (List<Video>) -> Unit
    ) {
        viewModel.executeUseCase(
            useCase = getMovieVideos,
            request = input
        ) { videos ->
            onSuccess(videos)
        }
    }
}