package com.santukis.buildsrc.dependencies

import com.santukis.buildsrc.versions.Versions

object iOS {

    object Ktor {
        const val ktorDarwin = "io.ktor:ktor-client-darwin:${Versions.Ktor.ktor}"
    }

    object SQLDelight {
        const val iosDriver = "com.squareup.sqldelight:native-driver:${Versions.SQLDelight.sqlDelight}"
    }
}