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
import com.santukis.injection.ViewModelModuleConstants.LOAD_COULD_LIKE_MOVIES_STRATEGY
import com.santukis.injection.ViewModelModuleConstants.LOAD_MOVIE_DETAIL_STRATEGY
import com.santukis.injection.ViewModelModuleConstants.LOAD_MOVIE_VIDEOS_STRATEGY
import com.santukis.injection.ViewModelModuleConstants.LOAD_NOW_PLAYING_MOVIES_STRATEGY
import com.santukis.injection.ViewModelModuleConstants.LOAD_POPULAR_MOVIES_STRATEGY
import com.santukis.injection.ViewModelModuleConstants.LOAD_UPCOMING_MOVIES_STRATEGY
import com.santukis.injection.ViewModelModuleConstants.MOVIES_MODULE_NAME
import com.santukis.injection.ViewModelModuleConstants.MOVIES_VIEW_MODEL
import com.santukis.injection.ViewModelModuleConstants.MOVIE_DETAIL_VIEW_MODEL
import com.santukis.injection.ViewModelModuleConstants.VIEW_MODELS_MODULE_NAME
import com.santukis.viewmodels.configuration.ConfigurationViewModel
import com.santukis.viewmodels.core.strategies.ViewModelStrategy
import com.santukis.viewmodels.home.DefaultMoviesViewModel
import com.santukis.viewmodels.home.MoviesViewModel
import com.santukis.viewmodels.home.entities.MoviesState
import com.santukis.viewmodels.home.strategies.LoadCouldLikeMoviesStrategy
import com.santukis.viewmodels.home.strategies.LoadNowPlayingMoviesStrategy
import com.santukis.viewmodels.home.strategies.LoadPopularMoviesStrategy
import com.santukis.viewmodels.home.strategies.LoadUpcomingMoviesStrategy
import com.santukis.viewmodels.moviedetail.DefaultMovieDetailViewModel
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel
import com.santukis.viewmodels.moviedetail.entities.MovieDetailState
import com.santukis.viewmodels.moviedetail.strategies.LoadMovieDetailStrategy
import com.santukis.viewmodels.moviedetail.strategies.LoadMovieVideosStrategy
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

    const val LOAD_MOVIE_DETAIL_STRATEGY = "loadMovieDetailStrategy"
    const val LOAD_MOVIE_VIDEOS_STRATEGY = "loadMovieVideosStrategy"
    const val LOAD_UPCOMING_MOVIES_STRATEGY = "loadUpcomingMoviesStrategy"
    const val LOAD_NOW_PLAYING_MOVIES_STRATEGY = "loadNowPlayingMoviesStrategy"
    const val LOAD_POPULAR_MOVIES_STRATEGY = "loadPopularMoviesStrategy"
    const val LOAD_COULD_LIKE_MOVIES_STRATEGY = "loadCouldLikeMoviesStrategy"
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
            loadNowPlayingMoviesStrategy = instance(LOAD_NOW_PLAYING_MOVIES_STRATEGY),
            loadUpcomingMoviesStrategy = instance(LOAD_UPCOMING_MOVIES_STRATEGY),
            loadPopularMoviesStrategy = instance(LOAD_POPULAR_MOVIES_STRATEGY),
            loadCouldLikeMoviesStrategy = instance(LOAD_COULD_LIKE_MOVIES_STRATEGY)
        )
    }

    bind<ViewModelStrategy<Unit, MoviesState>>(LOAD_NOW_PLAYING_MOVIES_STRATEGY) with provider {
        LoadNowPlayingMoviesStrategy(
            getNowPlayingMovies = instance(GET_NOW_PLAYING_MOVIES_USE_CASE)
        )
    }

    bind<ViewModelStrategy<Unit, MoviesState>>(LOAD_POPULAR_MOVIES_STRATEGY) with provider {
        LoadPopularMoviesStrategy(
            getPopularMovies = instance(GET_POPULAR_MOVIES_USE_CASE)
        )
    }

    bind<ViewModelStrategy<Unit, MoviesState>>(LOAD_UPCOMING_MOVIES_STRATEGY) with provider {
        LoadUpcomingMoviesStrategy(
            getUpcomingMovies = instance(GET_UPCOMING_MOVIES_USE_CASE)
        )
    }

    bind<ViewModelStrategy<Unit, MoviesState>>(LOAD_COULD_LIKE_MOVIES_STRATEGY) with provider {
        LoadCouldLikeMoviesStrategy(
            getMoviesByKeyword = instance(GET_MOVIES_BY_KEYWORD_USE_CASE)
        )
    }

    bind<MovieDetailViewModel>(tag = MOVIE_DETAIL_VIEW_MODEL) with provider {
        DefaultMovieDetailViewModel(
            loadMovieDetailStrategy = instance(LOAD_MOVIE_DETAIL_STRATEGY),
            loadMovieVideosStrategy = instance(LOAD_MOVIE_VIDEOS_STRATEGY)
        )
    }

    bind<ViewModelStrategy<String, MovieDetailState>>(tag = LOAD_MOVIE_DETAIL_STRATEGY) with provider {
        LoadMovieDetailStrategy(
            getMovieDetail = instance(GET_MOVIE_DETAIL_USE_CASE)
        )
    }

    bind<ViewModelStrategy<String, MovieDetailState>>(tag = LOAD_MOVIE_VIDEOS_STRATEGY) with provider {
        LoadMovieVideosStrategy(
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