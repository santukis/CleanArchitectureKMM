package com.santukis.injection

import com.santukis.injection.UseCasesConstants.GET_COUNTRIES_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_LANGUAGES_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_MOVIES_BY_KEYWORD_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_MOVIE_DETAIL_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_MOVIE_VIDEOS_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_NOW_PLAYING_MOVIES_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_POPULAR_MOVIES_USE_CASE
import com.santukis.injection.UseCasesConstants.GET_UPCOMING_MOVIES_USE_CASE
import com.santukis.injection.ViewModelModuleConstants.CONFIGURATION_MODULE_NAME
import com.santukis.injection.ViewModelModuleConstants.CONFIGURATION_VIEW_MODEL
import com.santukis.injection.ViewModelModuleConstants.MOVIES_MODULE_NAME
import com.santukis.injection.ViewModelModuleConstants.MOVIES_VIEW_MODEL
import com.santukis.injection.ViewModelModuleConstants.MOVIE_DETAIL_VIEW_MODEL
import com.santukis.injection.ViewModelModuleConstants.VIEW_MODELS_MODULE_NAME
import com.santukis.viewmodels.configuration.ConfigurationViewModel
import com.santukis.viewmodels.home.DefaultMoviesViewModel
import com.santukis.viewmodels.home.MoviesViewModel
import com.santukis.viewmodels.moviedetail.DefaultMovieDetailViewModel
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

internal object ViewModelModuleConstants {
    const val VIEW_MODELS_MODULE_NAME = "viewModelsModule"
    const val MOVIES_MODULE_NAME = "moviesViewModelModule"
    const val MOVIES_VIEW_MODEL = "moviesViewModel"
    const val MOVIE_DETAIL_VIEW_MODEL = "moviesDetailViewModel"

    const val CONFIGURATION_MODULE_NAME = "configurationViewModelModule"
    const val CONFIGURATION_VIEW_MODEL = "configurationViewModel"
}

internal fun viewModels() = DI.Module(
    name = VIEW_MODELS_MODULE_NAME,
    allowSilentOverride = true
) {
    import(movies())
    import(configuration())
}

private fun movies() = DI.Module(
    name = MOVIES_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<MoviesViewModel>(tag = MOVIES_VIEW_MODEL) with provider {
        DefaultMoviesViewModel(
            getNowPlayingMovies = instance(GET_NOW_PLAYING_MOVIES_USE_CASE),
            getUpcomingMovies = instance(GET_UPCOMING_MOVIES_USE_CASE),
            getPopularMovies = instance(GET_POPULAR_MOVIES_USE_CASE),
            getMoviesByKeyword = instance(GET_MOVIES_BY_KEYWORD_USE_CASE)
        )
    }

    bind<MovieDetailViewModel>(tag = MOVIE_DETAIL_VIEW_MODEL) with provider {
        DefaultMovieDetailViewModel(
            getMovieDetail = instance(GET_MOVIE_DETAIL_USE_CASE),
            getMovieVideos = instance(GET_MOVIE_VIDEOS_USE_CASE)
        )
    }
}

private fun configuration() = DI.Module(
    name = CONFIGURATION_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<ConfigurationViewModel>(tag = CONFIGURATION_VIEW_MODEL) with provider {
        ConfigurationViewModel(
            getCountries = instance(GET_COUNTRIES_USE_CASE),
            getLanguages = instance(GET_LANGUAGES_USE_CASE)
        )
    }
}