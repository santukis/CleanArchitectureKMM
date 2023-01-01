package com.santukis.injection

import com.santukis.viewmodels.movies.MovieViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.direct
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

fun getDependencyInjector(moduleDependencies: Any? = null): DependencyInjector = DependencyInjector.initialize(moduleDependencies)

@ThreadLocal
object DependencyInjector {

    private var kodeinDI: KodeinDI? = null

    fun initialize(moduleDependencies: Any? = null): DependencyInjector {
        kodeinDI = KodeinDI(moduleDependencies)
        return this
    }

    fun moviesViewModel(): MovieViewModel = kodeinDI?.getInstance(ViewModelModuleConstants.MOVIES_VIEW_MODEL) ?: throw Exception("Unable to load MovieViewModel")
}

internal class KodeinDI(private var moduleDependencies: Any? = null): DIAware {

    override val di: DI by DI.lazy {
        import(applicationModule(moduleDependencies))
    }
}

internal inline fun<reified Dependency> KodeinDI.getInstance(key: String): Dependency = di.direct.instance(key)