package com.santukis.datasources.remote

import com.santukis.datasources.remote.entities.ErrorDto
import com.santukis.datasources.remote.entities.ServerException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

expect fun buildClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

class KtorClient(engine: HttpClientEngine) {
    val httpClient = HttpClient(engine = engine) {
        expectSuccess = true

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    useArrayPolymorphism = true
                    ignoreUnknownKeys = true
                    allowStructuredMapKeys = true
                },
                contentType = ContentType.Application.Json
            )
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = "",
                        refreshToken = ""
                    )
                }
            }
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.DEFAULT
        }

        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, request ->
                val clientException = exception as? ClientRequestException ?: return@handleResponseExceptionWithRequest
                val error: ErrorDto = clientException.response.body()

                throw ServerException(error)
            }
        }
    }
}