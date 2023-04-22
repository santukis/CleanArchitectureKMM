package com.santukis.injection

import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.direct
import org.kodein.di.instance

internal class KodeinDI(private var moduleDependencies: Any? = null): DIAware {

    override val di: DI by DI.lazy {
        import(applicationModule(moduleDependencies))
    }
}

internal inline fun<reified Dependency> KodeinDI.getInstance(key: String): Dependency =
    di.direct.instance(key)