package com.santukis.injection

import com.santukis.viewmodels.home.MoviesViewModel
import com.santukis.viewmodels.moviedetail.MovieDetailViewModel

@ThreadLocal
internal object IosDependencyInjector: DependencyInjector {

    private var kodeinDI: KodeinDI? = null

    internal fun initialize(moduleDependencies: Any? = null) {
        if (kodeinDI == null) {
            kodeinDI = KodeinDI(moduleDependencies)
        }
    }

    override fun moviesViewModel(platformDependencies: Any?): MoviesViewModel =
        kodeinDI?.getInstance(ViewModelModuleConstants.MOVIES_VIEW_MODEL)
            ?: throw Exception("KodeinDI is not initialized")

    override fun movieDetailViewModel(platformDependencies: Any?): MovieDetailViewModel =
        kodeinDI?.getInstance(ViewModelModuleConstants.MOVIE_DETAIL_VIEW_MODEL)
            ?: throw Exception("KodeinDI is not initialized")
}