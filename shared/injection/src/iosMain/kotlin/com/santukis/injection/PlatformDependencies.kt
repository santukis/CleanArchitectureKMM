package com.santukis.injection

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

const val IOS_APPLICATION_MODULE = "IosApplicationModule"

actual fun platformModules(platformDependencies: Any?): DI.Module =
    DI.Module(
        name = IOS_APPLICATION_MODULE,
        allowSilentOverride = true
    ) {

        bind<DataStore<Preferences>>() with singleton {
            PreferenceDataStoreFactory.createWithPath(produceFile = {
                val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                    directory = NSDocumentDirectory,
                    inDomain = NSUserDomainMask,
                    appropriateForURL = null,
                    create = false,
                    error = null,
                )
                val path: String = requireNotNull(documentDirectory).path + "/configuration_preferences.preferences_pb"

                path.toPath()
            })
        }
    }
