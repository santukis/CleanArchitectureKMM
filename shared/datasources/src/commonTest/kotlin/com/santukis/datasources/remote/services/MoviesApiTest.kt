import com.santukis.datasources.remote.KtorClient
import com.santukis.datasources.remote.entities.ServerException
import com.santukis.datasources.remote.services.MoviesApi
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import kotlin.test.*

internal class MoviesApiTest {

    @Test
    fun getMovieDetailShouldThrowsServerExceptionWhenServiceReturnsAndError() {
        runBlocking {
            val mockEngine = MockEngine { request ->
                respond(
                    content = ByteReadChannel(
                        """
                            {
                              "success": false,
                              "status_code": 34,
                              "status_message": "The resource you requested could not be found."
                            }
                        """.trimIndent()),
                    status = HttpStatusCode.NotFound,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }

            kotlin.runCatching {
                val moviesApi = MoviesApi(KtorClient(mockEngine))
                moviesApi.getMovieDetail("anyInvalidId")

            }.onSuccess {
                fail("onFailure should be called")

            }.onFailure {
                assertEquals(ServerException::class, it::class)
                assertFalse((it as ServerException).error.success ?: true)
                assertEquals(34, it.error.statusCode)
                assertEquals("The resource you requested could not be found.", it.error.statusMessage)
            }
        }
    }

    @Test
    fun getMovieDetailShouldBuildUrlAsExpected() {
        runBlocking {
            val mockEngine = MockEngine { request ->
                respondBadRequest()
            }

            kotlin.runCatching {
                val moviesApi = MoviesApi(KtorClient(mockEngine))
                moviesApi.getMovieDetail("500")

            }.onFailure {
                assertEquals("https://api.themoviedb.org/3/movie/500", mockEngine.requestHistory.firstOrNull()?.url.toString())

            }.onSuccess {
                fail("onFailure should be called")
            }
        }
    }
}