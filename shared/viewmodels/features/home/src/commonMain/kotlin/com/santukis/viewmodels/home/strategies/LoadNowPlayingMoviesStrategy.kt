package com.santukis.viewmodels.home.strategies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.core.executeUseCase
import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow

class LoadNowPlayingMoviesStrategy(
    private val getNowPlayingMovies: UseCase<Unit, Flow<List<Movie>>>,
): ViewModelStrategy<Unit, List<Movie>> {

    override fun execute(
        viewModel: ViewModel,
        input: Unit,
        onSuccess: (List<Movie>) -> Unit
    ) {
        viewModel.executeUseCase(
            useCase = getNowPlayingMovies,
            request = input
        ) { movies ->
            onSuccess(movies)
        }
    }
}