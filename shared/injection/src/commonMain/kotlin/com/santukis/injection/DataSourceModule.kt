package com.santukis.injection

import com.santukis.datasources.movies.LocalMovieDataSource
import com.santukis.datasources.movies.RemoteMovieDataSource
import com.santukis.injection.DataSourceConstants.DATA_SOURCES_MODULE_NAME
import com.santukis.injection.DataSourceConstants.GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.MOVIES_MODULE_NAME
import com.santukis.injection.DataSourceConstants.SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL
import com.santukis.repositories.sources.GetMovieDetailDataSource
import com.santukis.repositories.sources.SaveMovieDetailDataSource
import org.kodein.di.*

object DataSourceConstants {
    const val DATA_SOURCES_MODULE_NAME = "dataSources"
    const val MOVIES_MODULE_NAME = "moviesDataSourcesModuleName"
    const val GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE = "getMovieDetailDataSourceFromRemote"
    const val SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL = "saveMovieDetailDataSourceIntoLocal"
}

fun dataSources() = DI.Module(
    name = DATA_SOURCES_MODULE_NAME,
    allowSilentOverride = true
) {

    import(movies())
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
