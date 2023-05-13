package com.santukis.injection.provider

import com.santukis.injection.DependencyInjector

expect fun initializeDependencyInjector(
    provider: DependencyInjectorProvider.Provider = DependencyInjectorProvider.Provider.KODEIN,
    moduleDependencies: Any? = null
): DependencyInjector

object DependencyInjectorProvider {

    private var dependencyInjector: DependencyInjector? = null

    fun initialize(
        provider: Provider = Provider.KODEIN,
        moduleDependencies: Any? = null
    ) {
        dependencyInjector = initializeDependencyInjector(provider, moduleDependencies)
    }

    fun get(): DependencyInjector = dependencyInjector
        ?: throw IllegalStateException("DependencyInjector not initialized")

    enum class Provider {
        KODEIN,
        TESTING
    }
}