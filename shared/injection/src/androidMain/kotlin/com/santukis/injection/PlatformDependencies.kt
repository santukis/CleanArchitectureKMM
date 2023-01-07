package com.santukis.injection

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.kodein.di.DI
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

const val ANDROID_APPLICATION_MODULE = "AndroidApplicationModule"

actual fun platformModules(platformDependencies: Any?): DI.Module =
    DI.Module(
        name = ANDROID_APPLICATION_MODULE,
        allowSilentOverride = true
    ) {
        (platformDependencies as? Application)?.let {
            import(androidXModule(it))
        }

        bind<DataStore<Preferences>>() with singleton {
            PreferenceDataStoreFactory.createWithPath(produceFile = {
                instance<Context>()
                    .filesDir
                    .resolve("configuration_preferences")
                    .absolutePath
                    .toPath()
            })
        }
    }
