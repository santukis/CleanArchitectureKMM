package com.santukis.viewmodels.core.strategies

import com.santukis.entities.movies.Movie
import com.santukis.usecases.UseCase
import com.santukis.viewmodels.core.executeUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.Flow

class LoadPaginatedMoviesStrategy(
    private val getMovies: UseCase<Int, Flow<List<Movie>>>
): ViewModelStrategy<Unit, List<Movie>> {

    private var page: Int = 1

    override fun execute(
        viewModel: ViewModel,
        input: Unit,
        onSuccess: (List<Movie>) -> Unit
    ) {
        viewModel.executeUseCase(
            useCase = getMovies,
            request = page++
        ) { movies ->
            onSuccess(movies)
        }
    }
}