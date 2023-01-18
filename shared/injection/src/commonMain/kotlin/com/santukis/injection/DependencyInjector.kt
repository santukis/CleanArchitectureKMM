package com.santukis.injection

import com.santukis.viewmodels.home.HomeViewModel
import com.santukis.viewmodels.movies.MovieDetailViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.direct
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

interface DependencyInjector {

    fun homeViewModel(): HomeViewModel

    fun movieDetailViewModel(): MovieDetailViewModel
}

fun getDependencyInjector(moduleDependencies: Any? = null): DependencyInjector =
    KodeinDependencyInjector.initialize(moduleDependencies)

@ThreadLocal
internal object KodeinDependencyInjector: DependencyInjector {

    private var kodeinDI: KodeinDI? = null

    internal fun initialize(moduleDependencies: Any? = null): DependencyInjector {
        if (kodeinDI == null) {
            kodeinDI = KodeinDI(moduleDependencies)
        }

        return this
    }

    override fun homeViewModel(): HomeViewModel =
        kodeinDI?.getInstance(ViewModelModuleConstants.HOME_VIEW_MODEL)
            ?: throw Exception("Unable to load HomeViewModel")

    override fun movieDetailViewModel(): MovieDetailViewModel =
        kodeinDI?.getInstance(ViewModelModuleConstants.MOVIE_DETAIL_VIEW_MODEL)
            ?: throw Exception("Unable to load MovieDetailViewModel")
}

internal class KodeinDI(private var moduleDependencies: Any? = null): DIAware {

    override val di: DI by DI.lazy {
        import(applicationModule(moduleDependencies))
    }

}

internal inline fun<reified Dependency> KodeinDI.getInstance(key: String): Dependency = di.direct.instance(key)