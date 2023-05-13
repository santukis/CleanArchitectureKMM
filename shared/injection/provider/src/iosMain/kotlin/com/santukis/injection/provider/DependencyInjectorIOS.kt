package com.santukis.injection.provider

import com.santukis.injection.DependencyInjector
import com.santukis.injection.kodein.KodeinIosDependencyInjector

actual fun initializeDependencyInjector(
    provider: DependencyInjectorProvider.Provider,
    moduleDependencies: Any?
): DependencyInjector =
    when (provider) {
        DependencyInjectorProvider.Provider.KODEIN -> KodeinIosDependencyInjector.initialize(moduleDependencies)
        DependencyInjectorProvider.Provider.TESTING -> throw Exception("Testing DependencyInjector not implemented")
    }
