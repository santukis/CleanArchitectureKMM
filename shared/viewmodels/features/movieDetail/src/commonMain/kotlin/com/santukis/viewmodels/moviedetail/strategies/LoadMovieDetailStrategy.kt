package com.santukis.viewmodels.moviedetail.strategies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.core.executeUseCase
import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import com.santukis.viewmodels.moviedetail.entities.MovieDetailState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow

class LoadMovieDetailStrategy(
    private val getMovieDetail: UseCase<String, Flow<Movie>>
): ViewModelStrategy<String, MovieDetailState> {

    override fun execute(
        viewModel: ViewModel,
        input: String,
        output: CMutableStateFlow<MovieDetailState>
    ) {
        viewModel.executeUseCase(
            useCase = getMovieDetail,
            request = input
        ) { movie ->
            output.value = output.value
                .copy(movie = movie)
        }
    }
}