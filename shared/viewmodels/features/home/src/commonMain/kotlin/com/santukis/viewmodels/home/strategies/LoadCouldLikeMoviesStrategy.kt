package com.santukis.viewmodels.home.strategies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.core.executeUseCase
import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import com.santukis.viewmodels.home.entities.MoviesState
import dev.icerock.moko.mvvm.flow.CMutableStateFlow
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow

class LoadCouldLikeMoviesStrategy(
    private val getMoviesByKeyword: UseCase<Unit, Flow<List<Movie>>>
): ViewModelStrategy<Unit, MoviesState> {

    override fun execute(
        viewModel: ViewModel,
        input: Unit,
        output: CMutableStateFlow<MoviesState>
    ) {
        viewModel.executeUseCase(
            useCase = getMoviesByKeyword,
            request = input
        ) { movies ->
            output.value = output.value
                .copy(couldLikeMovies = movies)
        }
    }
}