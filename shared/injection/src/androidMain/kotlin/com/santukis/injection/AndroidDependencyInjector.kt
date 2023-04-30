package com.santukis.injection

import androidx.lifecycle.ViewModelStoreOwner
import com.santukis.viewmodels.home.HomeViewModel
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel
import com.santukis.viewmodels.movies.MoviesViewModel
import dev.icerock.moko.mvvm.getViewModel

internal object AndroidDependencyInjector: DependencyInjector {

    private var kodeinDI: KodeinDI? = null

    internal fun initialize(moduleDependencies: Any? = null) {
        if (kodeinDI == null) {
            kodeinDI = KodeinDI(moduleDependencies)
        }
    }

    override fun homeViewModel(platformDependencies: Any?): HomeViewModel =
        (platformDependencies as? ViewModelStoreOwner)?.getViewModel {
            kodeinDI?.getInstance(ViewModelModuleConstants.HOME_VIEW_MODEL)
                ?: throw Exception("KodeinDI is not initialized")
        } ?: throw Exception("Unable to load MoviesViewModel")

    override fun movieDetailViewModel(platformDependencies: Any?): MovieDetailViewModel =
        (platformDependencies as? ViewModelStoreOwner)?.getViewModel {
            kodeinDI?.getInstance(ViewModelModuleConstants.MOVIE_DETAIL_VIEW_MODEL)
                ?: throw Exception("KodeinDI is not initialized")
        } ?: throw Exception("Unable to load MovieDetailViewModel")

    override fun moviesViewModel(platformDependencies: Any?): MoviesViewModel {
        return (platformDependencies as? ViewModelStoreOwner)?.getViewModel {
            kodeinDI?.getInstance(ViewModelModuleConstants.MOVIES_VIEW_MODEL)
                ?: throw Exception("KodeinDI is not initialized")
        } ?: throw Exception("Unable to load MoviesViewModel")
    }
}