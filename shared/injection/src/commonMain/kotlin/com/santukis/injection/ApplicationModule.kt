package com.santukis.injection

import org.kodein.di.DI
import repositories

const val APPLICATION_MODULE_NAME = "applicationModule"

fun applicationModule(platformDependencies: Any?) = DI.Module(
    name = APPLICATION_MODULE_NAME,
    allowSilentOverride = true
) {
    import(platformModules(platformDependencies))
    import(viewModels())
    import(useCases())
    import(repositories())
    import(dataSources())
    import(remote())
}