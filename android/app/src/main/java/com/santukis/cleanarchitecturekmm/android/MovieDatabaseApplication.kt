package com.santukis.cleanarchitecturekmm.android

import android.app.Application
import com.santukis.injection.initializeDependencyInjector

class MovieDatabaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeDependencyInjector(moduleDependencies = this)
    }
}