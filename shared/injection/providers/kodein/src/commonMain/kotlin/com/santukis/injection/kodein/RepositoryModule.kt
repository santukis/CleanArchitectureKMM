package com.santukis.injection.kodein

import com.santukis.injection.kodein.RepositoriesConstants.CONFIGURATION_MODULE_NAME
import com.santukis.injection.kodein.RepositoriesConstants.CONFIGURATION_REPOSITORY
import com.santukis.injection.kodein.RepositoriesConstants.GET_COUNTRIES_GATEWAY
import com.santukis.injection.kodein.RepositoriesConstants.GET_LANGUAGES_GATEWAY
import com.santukis.injection.kodein.RepositoriesConstants.GET_MOVIE_DETAIL_GATEWAY
import com.santukis.injection.kodein.RepositoriesConstants.GET_NOW_PLAYING_MOVIES_GATEWAY
import com.santukis.injection.kodein.RepositoriesConstants.GET_NOW_PLAYING_MOVIES_STRATEGY
import com.santukis.injection.kodein.RepositoriesConstants.GET_POPULAR_MOVIES_GATEWAY
import com.santukis.injection.kodein.RepositoriesConstants.GET_POPULAR_MOVIES_STRATEGY
import com.santukis.injection.kodein.RepositoriesConstants.GET_UPCOMING_MOVIES_GATEWAY
import com.santukis.injection.kodein.RepositoriesConstants.GET_UPCOMING_MOVIES_STRATEGY
import com.santukis.injection.kodein.RepositoriesConstants.MOVIES_MODULE_NAME
import com.santukis.injection.kodein.RepositoriesConstants.MOVIES_REPOSITORY
import com.santukis.injection.kodein.RepositoriesConstants.REPOSITORIES_MODULE_NAME
import com.santukis.entities.movies.Keyword
import com.santukis.entities.movies.Movie
import com.santukis.entities.movies.Video
import com.santukis.injection.kodein.DataSourceConstants.GET_COUNTRIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.kodein.DataSourceConstants.GET_LANGUAGES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.kodein.DataSourceConstants.GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.kodein.DataSourceConstants.GET_NOW_PLAYING_MOVIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.kodein.DataSourceConstants.GET_POPULAR_MOVIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.kodein.DataSourceConstants.GET_UPCOMING_MOVIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.kodein.DataSourceConstants.SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL
import com.santukis.repositories.configuration.ConfigurationRepository
import com.santukis.repositories.movies.MovieRepository
import com.santukis.repositories.movies.entities.GetMoviesByKeywordRequest
import com.santukis.repositories.movies.strategies.*
import com.santukis.repositories.strategies.RepositoryStrategy
import com.santukis.usecases.configuration.outputs.GetCountriesOutput
import com.santukis.usecases.configuration.outputs.GetLanguagesOutput
import com.santukis.usecases.movies.outputs.GetMovieDetailOutput
import com.santukis.usecases.movies.outputs.GetMovieVideosOutput
import com.santukis.usecases.movies.outputs.GetMoviesByKeywordOutput
import com.santukis.usecases.movies.outputs.GetNowPlayingMoviesOutput
import com.santukis.usecases.movies.outputs.GetPopularMoviesOutput
import com.santukis.usecases.movies.outputs.GetUpcomingMoviesOutput
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal object RepositoriesConstants {
    const val REPOSITORIES_MODULE_NAME = "repositories"
    const val MOVIES_MODULE_NAME = "moviesRepositoriesModuleName"
    const val MOVIES_REPOSITORY = "moviesRepository"
    const val GET_MOVIE_DETAIL_GATEWAY = "getMovieDetailGateway"
    const val GET_NOW_PLAYING_MOVIES_GATEWAY = "getNowPlayingMoviesDetailGateway"
    const val GET_UPCOMING_MOVIES_GATEWAY = "getUpcomingMoviesDetailGateway"
    const val GET_POPULAR_MOVIES_GATEWAY = "getPopularMoviesDetailGateway"

    const val GET_NOW_PLAYING_MOVIES_STRATEGY = "getNowPlayingMoviesStrategy"
    const val GET_UPCOMING_MOVIES_STRATEGY = "getUpcomingMoviesStrategy"
    const val GET_POPULAR_MOVIES_STRATEGY = "getPopularMoviesStrategy"

    const val CONFIGURATION_MODULE_NAME = "configurationRepositoriesModuleName"
    const val CONFIGURATION_REPOSITORY = "configurationRepository"
    const val GET_COUNTRIES_GATEWAY = "getCountriesGateway"
    const val GET_LANGUAGES_GATEWAY = "getLanguagesGateway"

}

internal fun repositories() = DI.Module(
    name = REPOSITORIES_MODULE_NAME,
    allowSilentOverride = true
) {
    import(movies())
    import(configuration())
}

private fun movies() = DI.Module(
    name = MOVIES_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<MovieRepository>(tag = MOVIES_REPOSITORY) with singleton {
        MovieRepository(
            getMovieDetailStrategy = instance(),
            getMovieVideosStrategy = instance(),
            getMovieKeywordsStrategy = instance(),
            getNowPlayingMoviesStrategy = instance(GET_NOW_PLAYING_MOVIES_STRATEGY),
            getUpcomingMoviesStrategy = instance(GET_UPCOMING_MOVIES_STRATEGY),
            getPopularMoviesStrategy = instance(GET_POPULAR_MOVIES_STRATEGY),
            getMostFrequentlyKeywordsStrategy = instance(),
            getMoviesByKeywordStrategy = instance()
        )
    }

    bind<RepositoryStrategy<String, Movie>>() with singleton {
        GetMovieDetailFromRemoteStrategy(
            getMovieDetailFromRemote = instance(GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE),
            saveMovieDetailToLocal = instance(SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL)
        )
    }

    bind<RepositoryStrategy<String, List<Video>>>() with singleton {
        GetMovieVideosFromRemoteStrategy(
            getMovieVideosFromRemote = instance()
        )
    }

    bind<RepositoryStrategy<String, List<Keyword>>>() with singleton {
        GetMovieKeywordsFromRemoteStrategy(
            getMovieKeywordsFromRemote = instance(),
            saveMovieKeywordsToLocal = instance()
        )
    }

    bind<RepositoryStrategy<Unit, List<Keyword>>>() with singleton {
        GetMostFrequentlyKeywordsFromLocalStrategy(
            getMostFrequentlyKeywordsFromLocal = instance()
        )
    }

    bind<RepositoryStrategy<Unit, List<Movie>>>(tag = GET_NOW_PLAYING_MOVIES_STRATEGY) with singleton {
        GetNowPlayingMoviesFromRemoteStrategy(
            getNowPlayingMoviesFromRemote = instance(GET_NOW_PLAYING_MOVIES_DATA_SOURCE_FROM_REMOTE)
        )
    }

    bind<RepositoryStrategy<Int, List<Movie>>>(tag = GET_UPCOMING_MOVIES_STRATEGY) with singleton {
        GetUpcomingMoviesFromRemoteStrategy(
            getUpcomingMoviesFromRemote = instance(GET_UPCOMING_MOVIES_DATA_SOURCE_FROM_REMOTE)
        )
    }

    bind<RepositoryStrategy<Int, List<Movie>>>(tag = GET_POPULAR_MOVIES_STRATEGY) with singleton {
        GetPopularMoviesFromRemoteStrategy(
            getPopularMoviesFromRemote = instance(GET_POPULAR_MOVIES_DATA_SOURCE_FROM_REMOTE)
        )
    }

    bind<RepositoryStrategy<GetMoviesByKeywordRequest, List<Movie>>>() with singleton {
        GetMoviesByKeywordFromRemoteStrategy(
            getMoviesByKeywordFromRemote = instance()
        )
    }

    bind<GetMovieDetailOutput>(tag = GET_MOVIE_DETAIL_GATEWAY) with singleton {
        instance<MovieRepository>(tag = MOVIES_REPOSITORY)
    }

    bind<GetMovieVideosOutput>() with singleton {
        instance<MovieRepository>(tag = MOVIES_REPOSITORY)
    }

    bind<GetNowPlayingMoviesOutput>(tag = GET_NOW_PLAYING_MOVIES_GATEWAY) with singleton {
        instance<MovieRepository>(tag = MOVIES_REPOSITORY)
    }

    bind<GetUpcomingMoviesOutput>(tag = GET_UPCOMING_MOVIES_GATEWAY) with singleton {
        instance<MovieRepository>(tag = MOVIES_REPOSITORY)
    }

    bind<GetPopularMoviesOutput>(tag = GET_POPULAR_MOVIES_GATEWAY) with singleton {
        instance<MovieRepository>(tag = MOVIES_REPOSITORY)
    }

    bind<GetMoviesByKeywordOutput>() with singleton {
        instance<MovieRepository>(tag = MOVIES_REPOSITORY)
    }
}

private fun configuration() = DI.Module(
    name = CONFIGURATION_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<ConfigurationRepository>(tag = CONFIGURATION_REPOSITORY) with singleton {
        ConfigurationRepository(
            instance(GET_COUNTRIES_DATA_SOURCE_FROM_REMOTE),
            instance(GET_LANGUAGES_DATA_SOURCE_FROM_REMOTE)
        )
    }

    bind<GetCountriesOutput>(tag = GET_COUNTRIES_GATEWAY) with singleton {
        instance<ConfigurationRepository>(tag = CONFIGURATION_REPOSITORY)
    }

    bind<GetLanguagesOutput>(tag = GET_LANGUAGES_GATEWAY) with singleton {
        instance<ConfigurationRepository>(tag = CONFIGURATION_REPOSITORY)
    }
}