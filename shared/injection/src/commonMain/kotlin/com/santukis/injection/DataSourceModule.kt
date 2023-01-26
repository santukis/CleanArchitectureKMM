package com.santukis.injection

import com.santukis.datasources.configuration.local.LocalConfigurationDataSource
import com.santukis.datasources.configuration.remote.RemoteConfigurationDataSource
import com.santukis.datasources.movies.local.LocalMovieDataSource
import com.santukis.datasources.movies.remote.RemoteMovieDataSource
import com.santukis.injection.DataSourceConstants.CONFIGURATION_MODULE_NAME
import com.santukis.injection.DataSourceConstants.DATA_SOURCES_MODULE_NAME
import com.santukis.injection.DataSourceConstants.GET_COUNTRIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.GET_LANGUAGES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.GET_NOW_PLAYING_MOVIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.GET_POPULAR_MOVIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.GET_REGION_DATA_SOURCE_FROM_LOCAL
import com.santukis.injection.DataSourceConstants.GET_UPCOMING_MOVIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.MOVIES_MODULE_NAME
import com.santukis.injection.DataSourceConstants.SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL
import com.santukis.injection.DataSourceConstants.SAVE_REGION_DATA_SOURCE_INTO_LOCAL
import com.santukis.repositories.configuration.sources.GetCountriesDataSource
import com.santukis.repositories.configuration.sources.GetLanguagesDataSource
import com.santukis.repositories.configuration.sources.GetRegionDataSource
import com.santukis.repositories.configuration.sources.SaveRegionDataSource
import com.santukis.repositories.movies.sources.*
import org.kodein.di.*

internal object DataSourceConstants {
    const val DATA_SOURCES_MODULE_NAME = "dataSources"
    const val MOVIES_MODULE_NAME = "moviesDataSourcesModuleName"
    const val GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE = "getMovieDetailDataSourceFromRemote"
    const val SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL = "saveMovieDetailDataSourceIntoLocal"
    const val GET_NOW_PLAYING_MOVIES_DATA_SOURCE_FROM_REMOTE = "getNowPlayingMoviesDataSourceFromRemote"
    const val GET_UPCOMING_MOVIES_DATA_SOURCE_FROM_REMOTE = "getUpcomingMoviesDataSourceFromRemote"
    const val GET_POPULAR_MOVIES_DATA_SOURCE_FROM_REMOTE = "getPopularMoviesDataSourceFromRemote"

    const val CONFIGURATION_MODULE_NAME = "configurationDataSourcesModuleName"
    const val GET_COUNTRIES_DATA_SOURCE_FROM_REMOTE = "getCountriesDataSourceFromRemote"
    const val GET_LANGUAGES_DATA_SOURCE_FROM_REMOTE = "getLanguagesDataSourceFromRemote"
    const val GET_REGION_DATA_SOURCE_FROM_LOCAL = "getRegionDataSourceFromRemote"
    const val SAVE_REGION_DATA_SOURCE_INTO_LOCAL = "saveRegionDataSourceFromRemote"
}

fun dataSources() = DI.Module(
    name = DATA_SOURCES_MODULE_NAME,
    allowSilentOverride = true
) {
    import(movies())
    import(configuration())
}

private fun movies() = DI.Module(
    name = MOVIES_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<RemoteMovieDataSource>() with singleton {
        RemoteMovieDataSource(
            moviesApi = instance(),
            getRegionDataSource = instance(GET_REGION_DATA_SOURCE_FROM_LOCAL)
        )
    }

    bind<LocalMovieDataSource>() with singleton {
        LocalMovieDataSource(
            database = instance(),
        )
    }

    bind<GetMovieDetailDataSource>(tag = GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE) with singleton {
        instance<RemoteMovieDataSource>()
    }

    bind<SaveMovieDetailDataSource>(tag = SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL) with provider {
        instance<LocalMovieDataSource>()
    }

    bind<GetKeywordsForMovieDataSource>() with singleton {
        instance<RemoteMovieDataSource>()
    }

    bind<SaveMovieKeywordsDataSource>() with provider {
        instance<LocalMovieDataSource>()
    }

    bind<GetNowPlayingMoviesDataSource>(tag = GET_NOW_PLAYING_MOVIES_DATA_SOURCE_FROM_REMOTE) with provider {
        instance<RemoteMovieDataSource>()
    }

    bind<GetUpcomingMoviesDataSource>(tag = GET_UPCOMING_MOVIES_DATA_SOURCE_FROM_REMOTE) with provider {
        instance<RemoteMovieDataSource>()
    }

    bind<GetPopularMoviesDataSource>(tag = GET_POPULAR_MOVIES_DATA_SOURCE_FROM_REMOTE) with provider {
        instance<RemoteMovieDataSource>()
    }

    bind<GetMostFrequentlyKeywordsDataSource>() with provider {
        instance<LocalMovieDataSource>()
    }

    bind<GetMoviesByKeywordDataSource>() with provider {
        instance<RemoteMovieDataSource>()
    }

    bind<GetMovieVideosDataSource>() with provider {
        instance<RemoteMovieDataSource>()
    }
}

private fun configuration() = DI.Module(
    name = CONFIGURATION_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<GetCountriesDataSource>(tag = GET_COUNTRIES_DATA_SOURCE_FROM_REMOTE) with singleton {
        RemoteConfigurationDataSource(configurationApi = instance())
    }

    bind<GetLanguagesDataSource>(tag = GET_LANGUAGES_DATA_SOURCE_FROM_REMOTE) with singleton {
        RemoteConfigurationDataSource(configurationApi = instance())
    }

    bind<GetRegionDataSource>(tag = GET_REGION_DATA_SOURCE_FROM_LOCAL) with singleton {
        LocalConfigurationDataSource(instance())
    }

    bind<SaveRegionDataSource>(tag = SAVE_REGION_DATA_SOURCE_INTO_LOCAL) with singleton {
        LocalConfigurationDataSource(instance())
    }
}