package com.santukis.injection.koin

import org.koin.core.context.startKoin

internal class KoinDI(private val moduleDependencies: Any? = null) {

    init {
        startKoin {
            modules(
                applicationModule(moduleDependencies)
            )
        }
    }
}