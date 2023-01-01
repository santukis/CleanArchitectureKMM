import RepositoriesConstants.GET_MOVIE_DETAIL_GATEWAY
import RepositoriesConstants.MOVIES_MODULE_NAME
import RepositoriesConstants.MOVIES_REPOSITORY
import RepositoriesConstants.REPOSITORIES_MODULE_NAME
import com.santukis.injection.DataSourceConstants.GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL
import com.santukis.repositories.movies.MovieRepository
import com.santukis.usecases.movies.GetMovieDetailGateway
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

internal object RepositoriesConstants {
    const val REPOSITORIES_MODULE_NAME = "repositories"
    const val MOVIES_MODULE_NAME = "moviesRepositoriesModuleName"
    const val MOVIES_REPOSITORY = "moviesRepository"
    const val GET_MOVIE_DETAIL_GATEWAY = "getMovieDetailGateway"
}

internal fun repositories() = DI.Module(
    name = REPOSITORIES_MODULE_NAME,
    allowSilentOverride = true
) {
    import(movies())
}

private fun movies() = DI.Module(
    name = MOVIES_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<MovieRepository>(tag = MOVIES_REPOSITORY) with singleton {
        MovieRepository(
            instance(GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE),
            instance(SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL)
        )
    }

    bind<GetMovieDetailGateway>(tag = GET_MOVIE_DETAIL_GATEWAY) with singleton {
        instance<MovieRepository>(tag = MOVIES_REPOSITORY)
    }
}