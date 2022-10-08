package com.santukis.injection

import org.kodein.di.DI

actual fun platformModules(platformDependencies: Any?): DI.Module =
    DI.Module(
        name = IOS_APPLICATION_MODULE,
        allowSilentOverride = true
    ) {

    }