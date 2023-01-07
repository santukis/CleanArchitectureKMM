import RepositoriesConstants.CONFIGURATION_MODULE_NAME
import RepositoriesConstants.CONFIGURATION_REPOSITORY
import RepositoriesConstants.GET_COUNTRIES_GATEWAY
import RepositoriesConstants.GET_LANGUAGES_GATEWAY
import RepositoriesConstants.GET_MOVIE_DETAIL_GATEWAY
import RepositoriesConstants.GET_NOW_PLAYING_MOVIES_GATEWAY
import RepositoriesConstants.MOVIES_MODULE_NAME
import RepositoriesConstants.MOVIES_REPOSITORY
import RepositoriesConstants.REPOSITORIES_MODULE_NAME
import com.santukis.injection.DataSourceConstants.GET_COUNTRIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.GET_LANGUAGES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.GET_NOW_PLAYING_MOVIES_DATA_SOURCE_FROM_REMOTE
import com.santukis.injection.DataSourceConstants.SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL
import com.santukis.repositories.configuration.ConfigurationRepository
import com.santukis.repositories.movies.MovieRepository
import com.santukis.usecases.configuration.GetCountriesGateway
import com.santukis.usecases.configuration.GetLanguagesGateway
import com.santukis.usecases.movies.GetMovieDetailGateway
import com.santukis.usecases.movies.GetNowPlayingMoviesGateway
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
            getMovieDetailFromRemote = instance(GET_MOVIE_DETAIL_DATA_SOURCE_FROM_REMOTE),
            saveMovieDetailToLocal = instance(SAVE_MOVIE_DETAIL_DATA_SOURCE_INTO_LOCAL),
            getNowPlayingMoviesDataSource = instance(GET_NOW_PLAYING_MOVIES_DATA_SOURCE_FROM_REMOTE)
        )
    }

    bind<GetMovieDetailGateway>(tag = GET_MOVIE_DETAIL_GATEWAY) with singleton {
        instance<MovieRepository>(tag = MOVIES_REPOSITORY)
    }

    bind<GetNowPlayingMoviesGateway>(tag = GET_NOW_PLAYING_MOVIES_GATEWAY) with singleton {
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

    bind<GetCountriesGateway>(tag = GET_COUNTRIES_GATEWAY) with singleton {
        instance<ConfigurationRepository>(tag = CONFIGURATION_REPOSITORY)
    }

    bind<GetLanguagesGateway>(tag = GET_LANGUAGES_GATEWAY) with singleton {
        instance<ConfigurationRepository>(tag = CONFIGURATION_REPOSITORY)
    }
}