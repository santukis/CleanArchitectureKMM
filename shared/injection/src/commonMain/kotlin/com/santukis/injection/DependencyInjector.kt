package com.santukis.injection

import com.santukis.viewmodels.movies.MovieDetailViewModel
import com.santukis.viewmodels.movies.MoviesViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.direct
import org.kodein.di.instance

interface DependencyInjector {

    fun moviesViewModel(platformDependencies: Any? = null): MoviesViewModel

    fun movieDetailViewModel(platformDependencies: Any? = null): MovieDetailViewModel
}

expect fun getDependencyInjector(moduleDependencies: Any? = null): DependencyInjector

internal class KodeinDI(private var moduleDependencies: Any? = null): DIAware {

    override val di: DI by DI.lazy {
        import(applicationModule(moduleDependencies))
    }
}

internal inline fun<reified Dependency> KodeinDI.getInstance(key: String): Dependency = di.direct.instance(key)