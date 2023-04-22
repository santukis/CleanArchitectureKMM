package com.santukis.injection


actual fun getDependencyInjector(moduleDependencies: Any?): DependencyInjector =
    AndroidDependencyInjector.initialize(moduleDependencies)