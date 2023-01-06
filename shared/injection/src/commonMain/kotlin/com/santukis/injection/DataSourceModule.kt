package com.santukis.injection

import com.santukis.datasources.configuration.remote.RemoteConfigurationDataSource
import com.santukis.datasources.movies.local.LocalMovieDataSource
import com.santukis.datasources.movies.remote.RemoteMovieDataSource
import com.santukis.injection.DataSourceConstants.CONFIGURATION_MODULE_NAME
import com.santukis.injection.DataSourceConstants.DATA_SOURCES_MODULE_NAME
import com.santukis.injection.DataSourceConstants.GET_COUNTRIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.GET_LANGUAGES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.MOVIES_MODULE_NAME
import com.santukis.injection.DataSourceConstants.SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL
import com.santukis.repositories.configuration.sources.GetCountriesDataSource
import com.santukis.repositories.configuration.sources.GetLanguagesDataSource
import com.santukis.repositories.movies.sources.GetMovieDetailDataSource
import com.santukis.repositories.movies.sources.SaveMovieDetailDataSource
import org.kodein.di.*

internal object DataSourceConstants {
    const val DATA_SOURCES_MODULE_NAME = "dataSources"
    const val MOVIES_MODULE_NAME = "moviesDataSourcesModuleName"
    const val GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE = "getMovieDetailDataSourceFromRemote"
    const val SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL = "saveMovieDetailDataSourceIntoLocal"
    const val CONFIGURATION_MODULE_NAME = "configurationDataSourcesModuleName"
    const val GET_COUNTRIES_DATA_SOURCE_FROM_REMOTE = "getCountriesDataSourceFromRemote"
    const val GET_LANGUAGES_DATA_SOURCE_FROM_REMOTE = "getLanguagesDataSourceFromRemote"
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
    bind<GetMovieDetailDataSource>(tag = GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE) with provider {
        RemoteMovieDataSource(moviesApi = instance())
    }

    bind<SaveMovieDetailDataSource>(tag = SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL) with provider {
        LocalMovieDataSource()
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
}
