package com.santukis.injection.koin

import org.koin.core.module.Module
import org.koin.dsl.module

internal fun applicationModule(platformDependencies: Any?): Module = module {
    includes(
        platformModules(platformDependencies)
    )
}