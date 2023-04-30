package com.santukis.viewmodels.moviedetail.strategies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.core.executeUseCase
import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow

class LoadMovieDetailStrategy(
    private val getMovieDetail: UseCase<String, Flow<Movie>>
): ViewModelStrategy<String, Movie> {

    override fun execute(
        viewModel: ViewModel,
        input: String,
        onSuccess: (Movie) -> Unit
    ) {
        viewModel.executeUseCase(
            useCase = getMovieDetail,
            request = input
        ) { movie ->
            onSuccess(movie)
        }
    }
}