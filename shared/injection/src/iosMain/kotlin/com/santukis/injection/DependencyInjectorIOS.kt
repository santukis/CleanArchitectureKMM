package com.santukis.injection

actual fun getDependencyInjector(moduleDependencies: Any?): DependencyInjector =
    IosDependencyInjector.initialize(moduleDependencies)

