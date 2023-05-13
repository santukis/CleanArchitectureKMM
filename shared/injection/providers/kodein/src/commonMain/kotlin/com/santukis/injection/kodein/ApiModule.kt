package com.santukis.injection.kodein

import com.santukis.datasources.configuration.remote.services.ConfigurationApi
import com.santukis.datasources.remote.KtorClient
import com.santukis.datasources.remote.getHttpClientEngine
import com.santukis.datasources.movies.remote.services.MoviesApi
import com.santukis.injection.kodein.ApiModuleConstants.API_MODULE_NAME
import com.santukis.injection.kodein.ApiModuleConstants.HTTP_CLIENT_MODULE_NAME
import com.santukis.injection.kodein.ApiModuleConstants.REMOTE_MODULE_NAME
import org.kodein.di.*

internal object ApiModuleConstants {
    const val REMOTE_MODULE_NAME = "remoteModule"
    const val HTTP_CLIENT_MODULE_NAME = "httpClientModule"
    const val API_MODULE_NAME = "apiModule"
}

internal fun remote() = DI.Module(
    name = REMOTE_MODULE_NAME,
    allowSilentOverride = true
) {
    import(httpClient())
    import(api())
}

private fun httpClient() = DI.Module(
    name = HTTP_CLIENT_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<KtorClient>() with singleton { KtorClient(getHttpClientEngine()) }
}

private fun api() = DI.Module(
    name = API_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<MoviesApi>() with provider { MoviesApi(client = instance()) }

    bind<ConfigurationApi>() with provider { ConfigurationApi(client = instance()) }
}