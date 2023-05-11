package com.santukis.injection

import com.santukis.datasources.local.DatabaseDriverFactory
import com.santukis.datasources.local.MovieDatabase
import com.santukis.injection.DatabaseModuleConstants.DATABASE_MODULE_NAME
import com.santukis.injection.DatabaseModuleConstants.LOCAL_MODULE_NAME
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.eagerSingleton
import org.kodein.di.instance

internal object DatabaseModuleConstants {
    const val LOCAL_MODULE_NAME = "localModule"
    const val DATABASE_MODULE_NAME = "databaseModule"
}

internal fun local() = DI.Module(
    name = LOCAL_MODULE_NAME,
    allowSilentOverride = true
) {
    import(database())
}

private fun database() = DI.Module(
    name = DATABASE_MODULE_NAME,
    allowSilentOverride = true
) {
   bind<MovieDatabase>() with eagerSingleton {
       MovieDatabase(instance<DatabaseDriverFactory>().createDriver())
   }
}