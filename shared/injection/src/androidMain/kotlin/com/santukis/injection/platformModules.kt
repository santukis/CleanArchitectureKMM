package com.santukis.injection

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.android.x.androidXModule

actual fun platformModules(platformDependencies: Any?): DI.Module =
    DI.Module(
        name = ANDROID_APPLICATION_MODULE,
        allowSilentOverride = true
    ) {
        (platformDependencies as? Application)?.let {
            import(androidXModule(it))
        }
    }