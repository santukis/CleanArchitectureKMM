package com.santukis.injection.kodein

import org.kodein.di.DI

expect fun platformModules(platformDependencies: Any? = null): DI.Module
