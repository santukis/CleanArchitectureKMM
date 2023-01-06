package com.santukis.injection

import com.santukis.datasources.configuration.remote.services.ConfigurationApi
import com.santukis.datasources.core.remote.KtorClient
import com.santukis.datasources.core.remote.getHttpClientEngine
import com.santukis.datasources.movies.remote.services.MoviesApi
import com.santukis.injection.ApiModuleConstants.API_MODULE_NAME
import com.santukis.injection.ApiModuleConstants.HTTP_CLIENT_MODULE_NAME
import com.santukis.injection.ApiModuleConstants.REMOTE_MODULE_NAME
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