package com.santukis.injection

import androidx.lifecycle.ViewModelStoreOwner
import com.santukis.viewmodels.home.MoviesViewModel
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel
import dev.icerock.moko.mvvm.getViewModel

internal object AndroidDependencyInjector: DependencyInjector {

    private var kodeinDI: KodeinDI? = null

    internal fun initialize(moduleDependencies: Any? = null) {
        if (kodeinDI == null) {
            kodeinDI = KodeinDI(moduleDependencies)
        }
    }

    override fun moviesViewModel(platformDependencies: Any?): MoviesViewModel =
        (platformDependencies as? ViewModelStoreOwner)?.getViewModel {
            kodeinDI?.getInstance(ViewModelModuleConstants.MOVIES_VIEW_MODEL)
                ?: throw Exception("KodeinDI is not initialized")
        } ?: throw Exception("Unable to load MoviesViewModel")

    override fun movieDetailViewModel(platformDependencies: Any?): MovieDetailViewModel =
        (platformDependencies as? ViewModelStoreOwner)?.getViewModel {
            kodeinDI?.getInstance(ViewModelModuleConstants.MOVIE_DETAIL_VIEW_MODEL)
                ?: throw Exception("KodeinDI is not initialized")
        } ?: throw Exception("Unable to load MovieDetailViewModel")
}