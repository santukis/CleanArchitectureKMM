package com.santukis.injection

import RepositoriesConstants.GET_MOVIE_DETAIL_GATEWAY
import com.santukis.entities.movies.Movie
import com.santukis.injection.UseCasesConstants.GET_MOVIE_DETAIL_USE_CASE
import com.santukis.injection.UseCasesConstants.MOVIES_MODULE_NAME
import com.santukis.injection.UseCasesConstants.USE_CASES_MODULE_NAME
import com.santukis.usecases.UseCase
import com.santukis.usecases.movies.GetMovieDetail
import kotlinx.coroutines.flow.Flow
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

internal object UseCasesConstants {
    const val USE_CASES_MODULE_NAME = "useCases"
    const val MOVIES_MODULE_NAME = "moviesUseCasesModuleName"
    const val GET_MOVIE_DETAIL_USE_CASE = "getMovieDetail"
}

internal fun useCases() = DI.Module(
    name = USE_CASES_MODULE_NAME,
    allowSilentOverride = true
) {
    import(movies())
}

private fun movies() = DI.Module(
    name = MOVIES_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<UseCase<String, Flow<Movie>>>(tag = GET_MOVIE_DETAIL_USE_CASE) with provider {
        GetMovieDetail(instance(GET_MOVIE_DETAIL_GATEWAY))
    }
}