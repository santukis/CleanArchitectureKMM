package com.santukis.injection

import androidx.lifecycle.ViewModelStoreOwner
import com.santukis.viewmodels.movies.MovieDetailViewModel
import com.santukis.viewmodels.movies.MoviesViewModel
import dev.icerock.moko.mvvm.getViewModel

internal object AndroidDependencyInjector: DependencyInjector {

    private var kodeinDI: KodeinDI? = null

    internal fun initialize(moduleDependencies: Any? = null): DependencyInjector {
        if (kodeinDI == null) {
            kodeinDI = KodeinDI(moduleDependencies)
        }

        return this
    }

    override fun moviesViewModel(platformDependencies: Any?): MoviesViewModel =
        (platformDependencies as? ViewModelStoreOwner)?.getViewModel {
            kodeinDI?.getInstance(ViewModelModuleConstants.MOVIES_VIEW_MODEL)
                ?: throw Exception("Unable to load HomeViewModel")
        } ?: throw Exception("Unable to load HomeViewModel")

    override fun movieDetailViewModel(platformDependencies: Any?): MovieDetailViewModel =
        (platformDependencies as? ViewModelStoreOwner)?.getViewModel {
            kodeinDI?.getInstance(ViewModelModuleConstants.MOVIE_DETAIL_VIEW_MODEL)
                ?: throw Exception("Unable to load MovieDetailViewModel")
        } ?: throw Exception("Unable to load MovieDetailViewModel")
}