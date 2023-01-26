package com.santukis.injection

import RepositoriesConstants.GET_COUNTRIES_GATEWAY
import RepositoriesConstants.GET_LANGUAGES_GATEWAY
import RepositoriesConstants.GET_MOVIE_DETAIL_GATEWAY
import RepositoriesConstants.GET_NOW_PLAYING_MOVIES_GATEWAY
import RepositoriesConstants.GET_POPULAR_MOVIES_GATEWAY
import RepositoriesConstants.GET_UPCOMING_MOVIES_GATEWAY
import com.santukis.entities.configuration.Country
import com.santukis.entities.configuration.Language
import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.Video
import com.santukis.injection.UseCasesConstants.CONFIGURATION_MODULE_NAME
import com.santukis.injection.UseCasesConstants.GET_COUNTRIES_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_LANGUAGES_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_MOVIES_BY_KEYWORD_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_MOVIE_DETAIL_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_MOVIE_VIDEOS_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_NOW_PLAYING_MOVIES_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_POPULAR_MOVIES_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_UPCOMING_MOVIES_USE_CASE
import com.santukis.injection.UseCasesConstants.MOVIES_MODULE_NAME
import com.santukis.injection.UseCasesConstants.USE_CASES_MODULE_NAME
import com.santukis.usecases.UseCase
import com.santukis.usecases.configuration.GetCountries
import com.santukis.usecases.configuration.GetLanguages
import com.santukis.usecases.movies.*
import kotlinx.coroutines.flow.Flow
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

internal object UseCasesConstants {
    const val USE_CASES_MODULE_NAME = "useCases"
    const val MOVIES_MODULE_NAME = "moviesUseCasesModuleName"
    const val GET_MOVIE_DETAIL_USE_CASE = "getMovieDetail"
    const val GET_MOVIE_VIDEOS_USE_CASE = "getMovieVideos"
    const val GET_NOW_PLAYING_MOVIES_USE_CASE = "getNowPlayingMoviesDetail"
    const val GET_UPCOMING_MOVIES_USE_CASE = "getUpcomingMoviesDetail"
    const val GET_POPULAR_MOVIES_USE_CASE = "getPopularMoviesDetail"
    const val GET_MOVIES_BY_KEYWORD_USE_CASE = "getMoviesByKeywordDetail"

    const val CONFIGURATION_MODULE_NAME = "configurationUseCasesModuleName"
    const val GET_COUNTRIES_USE_CASE = "getCountriesDetail"
    const val GET_LANGUAGES_USE_CASE = "getLanguagesDetail"
}

internal fun useCases() = DI.Module(
    name = USE_CASES_MODULE_NAME,
    allowSilentOverride = true
) {
    import(movies())
    import(configuration())
}

private fun movies() = DI.Module(
    name = MOVIES_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<UseCase<String, Flow<Movie>>>(tag = GET_MOVIE_DETAIL_USE_CASE) with provider {
        GetMovieDetail(instance(GET_MOVIE_DETAIL_GATEWAY))
    }

    bind<UseCase<String, Flow<List<Video>>>>(tag = GET_MOVIE_VIDEOS_USE_CASE) with provider {
        GetMovieVideos(instance())
    }

    bind<UseCase<Unit, Flow<List<Movie>>>>(tag = GET_NOW_PLAYING_MOVIES_USE_CASE) with provider {
        GetNowPlayingMovies(instance(GET_NOW_PLAYING_MOVIES_GATEWAY))
    }

    bind<UseCase<Unit, Flow<List<Movie>>>>(tag = GET_UPCOMING_MOVIES_USE_CASE) with provider {
        GetUpcomingMovies(instance(GET_UPCOMING_MOVIES_GATEWAY))
    }

    bind<UseCase<Unit, Flow<List<Movie>>>>(tag = GET_POPULAR_MOVIES_USE_CASE) with provider {
        GetPopularMovies(instance(GET_POPULAR_MOVIES_GATEWAY))
    }

    bind<UseCase<Unit, Flow<List<Movie>>>>(tag = GET_MOVIES_BY_KEYWORD_USE_CASE) with provider {
        GetMoviesByKeyword(instance())
    }
}

private fun configuration() = DI.Module(
    name = CONFIGURATION_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<UseCase<Unit, List<Country>>>(tag = GET_COUNTRIES_USE_CASE) with provider {
        GetCountries(instance(GET_COUNTRIES_GATEWAY))
    }

    bind<UseCase<Unit, List<Language>>>(tag = GET_LANGUAGES_USE_CASE) with provider {
        GetLanguages(instance(GET_LANGUAGES_GATEWAY))
    }
}