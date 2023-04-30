package com.santukis.injection


actual fun initializeDependencyInjector(moduleDependencies: Any?) {
    AndroidDependencyInjector.initialize(moduleDependencies)
}

actual fun getDependencyInjector(): DependencyInjector = AndroidDependencyInjector
