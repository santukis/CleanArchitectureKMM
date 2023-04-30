package com.santukis.injection

import com.santukis.viewmodels.home.HomeViewModel
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel
import com.santukis.viewmodels.movies.MoviesViewModel

@ThreadLocal
internal object IosDependencyInjector: DependencyInjector {

    private var kodeinDI: KodeinDI? = null

    internal fun initialize(moduleDependencies: Any? = null) {
        if (kodeinDI == null) {
            kodeinDI = KodeinDI(moduleDependencies)
        }
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