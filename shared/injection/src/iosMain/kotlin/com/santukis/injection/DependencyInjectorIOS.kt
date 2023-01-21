package com.santukis.injection

import com.santukis.viewmodels.movies.MovieDetailViewModel
import com.santukis.viewmodels.movies.MoviesViewModel

actual fun getDependencyInjector(moduleDependencies: Any?): DependencyInjector =
    IosDependencyInjector.initialize(moduleDependencies)

@ThreadLocal
internal object IosDependencyInjector: DependencyInjector {

    private var kodeinDI: KodeinDI? = null

    internal fun initialize(moduleDependencies: Any? = null): DependencyInjector {
        if (kodeinDI == null) {
            kodeinDI = KodeinDI(moduleDependencies)
        }

        return this
    }

    override fun moviesViewModel(platformDependencies: Any?): MoviesViewModel =
        kodeinDI?.getInstance(ViewModelModuleConstants.MOVIES_VIEW_MODEL)
            ?: throw Exception("Unable to load HomeViewModel")

    override fun movieDetailViewModel(platformDependencies: Any?): MovieDetailViewModel =
        kodeinDI?.getInstance(ViewModelModuleConstants.MOVIE_DETAIL_VIEW_MODEL)
            ?: throw Exception("Unable to load MovieDetailViewModel")
}