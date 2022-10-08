package com.santukis.injection

import com.santukis.viewmodels.movies.MovieViewModel
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.direct
import org.kodein.di.instance
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object DependencyInjector: DIAware {

    var moduleDependencies: Any? = null

    override val di: DI by DI.lazy {
        import(applicationModule(moduleDependencies))
    }

    fun moviesViewModel(): MovieViewModel = di.direct.instance(ViewModelModuleConstants.MOVIES_VIEW_MODEL)
}