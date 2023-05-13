package com.santukis.injection.kodein

import androidx.lifecycle.ViewModelStoreOwner
import com.santukis.injection.DependencyInjector
import com.santukis.viewmodels.home.HomeViewModel
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel
import com.santukis.viewmodels.movies.MoviesViewModel
import dev.icerock.moko.mvvm.getViewModel

object KodeinAndroidDependencyInjector: DependencyInjector {

    private var kodeinDI: KodeinDI? = null

    fun initialize(moduleDependencies: Any? = null): DependencyInjector {
        if (kodeinDI == null) {
            kodeinDI = KodeinDI(moduleDependencies)
        }

        return this
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