package com.santukis.injection

actual fun initializeDependencyInjector(moduleDependencies: Any?) {
    IosDependencyInjector.initialize(moduleDependencies)
}

actual fun getDependencyInjector(): DependencyInjector = IosDependencyInjector

