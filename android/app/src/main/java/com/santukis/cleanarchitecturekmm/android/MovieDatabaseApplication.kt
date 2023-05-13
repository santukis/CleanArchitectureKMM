package com.santukis.cleanarchitecturekmm.android

import android.app.Application
import com.santukis.injection.provider.DependencyInjectorProvider

class MovieDatabaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyInjectorProvider.initialize(moduleDependencies = this)
    }
}