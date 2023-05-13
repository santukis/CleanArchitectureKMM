package com.santukis.injection.kodein

import org.kodein.di.DI

internal const val APPLICATION_MODULE_NAME = "applicationModule"

internal fun applicationModule(platformDependencies: Any?) = DI.Module(
    name = APPLICATION_MODULE_NAME,
    allowSilentOverride = true
) {
    import(platformModules(platformDependencies))
    import(viewModels())
    import(useCases())
    import(repositories())
    import(dataSources())
    import(remote())
    import(local())
}