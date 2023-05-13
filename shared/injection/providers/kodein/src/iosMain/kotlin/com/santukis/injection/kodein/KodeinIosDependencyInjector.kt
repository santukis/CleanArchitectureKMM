package com.santukis.injection.kodein

import com.santukis.injection.DependencyInjector
import com.santukis.injection.kodein.KodeinDI
import com.santukis.viewmodels.home.HomeViewModel
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel
import com.santukis.viewmodels.movies.MoviesViewModel
import com.santukis.injection.kodein.ViewModelModuleConstants.HOME_VIEW_MODEL
import com.santukis.injection.kodein.ViewModelModuleConstants.MOVIE_DETAIL_VIEW_MODEL

@ThreadLocal
object KodeinIosDependencyInjector: DependencyInjector {

    private var kodeinDI: KodeinDI? = null

    fun initialize(moduleDependencies: Any? = null): DependencyInjector {
        if (kodeinDI == null) {
            kodeinDI = KodeinDI(moduleDependencies)
        }

        return this
    }

    override fun homeViewModel(platformDependencies: Any?): HomeViewModel =
        kodeinDI?.getInstance(ViewModelModuleConstants.HOME_VIEW_MODEL)
            ?: throw Exception("KodeinDI is not initialized")

    override fun movieDetailViewModel(platformDependencies: Any?): MovieDetailViewModel =
        kodeinDI?.getInstance(ViewModelModuleConstants.MOVIE_DETAIL_VIEW_MODEL)
            ?: throw Exception("KodeinDI is not initialized")

    override fun moviesViewModel(platformDependencies: Any?): MoviesViewModel =
        kodeinDI?.getInstance(ViewModelModuleConstants.MOVIES_VIEW_MODEL)
            ?: throw Exception("KodeinDI is not initialized")
}