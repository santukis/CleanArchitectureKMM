package com.santukis.injection

import com.santukis.injection.UseCasesConstants.GET_MOVIE_DETAIL_USE_CASE
import com.santukis.injection.ViewModelModuleConstants.MOVIES_MODULE_NAME
import com.santukis.injection.ViewModelModuleConstants.MOVIES_VIEW_MODEL
import com.santukis.injection.ViewModelModuleConstants.VIEW_MODELS_MODULE_NAME
import com.santukis.viewmodels.movies.MovieViewModel
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider

internal object ViewModelModuleConstants {
    const val VIEW_MODELS_MODULE_NAME = "viewModelsModule"
    const val MOVIES_MODULE_NAME = "moviesViewModelModule"
    const val MOVIES_VIEW_MODEL = "moviesViewModel"
}

internal fun viewModels() = DI.Module(
    name = VIEW_MODELS_MODULE_NAME,
    allowSilentOverride = true
) {
    import(movies())
}

private fun movies() = DI.Module(
    name = MOVIES_MODULE_NAME,
    allowSilentOverride = true
) {
    bind<MovieViewModel>(tag = MOVIES_VIEW_MODEL) with provider {
        MovieViewModel(
            getMovieDetail = instance(GET_MOVIE_DETAIL_USE_CASE)
        )
    }
}