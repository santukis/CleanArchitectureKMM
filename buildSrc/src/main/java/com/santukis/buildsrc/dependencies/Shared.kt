package com.santukis.buildsrc.dependencies

import com.santukis.buildsrc.versions.Versions

object Shared {

    object Test {
        const val junit4 = "junit:junit:${Versions.Test.junit4}"
        const val junit5 = "org.junit.jupiter:junit-jupiter:${Versions.Test.junit5}"
        const val mockK = "io.mockk:mockk:${Versions.Test.mockk}"
        const val mockKJvm = "io.mockk:mockk-agent-jvm:${Versions.Test.mockk}"
        const val ktorMock = "io.ktor:ktor-client-mock:${Versions.Ktor.ktor}"
    }

    object Kotlin {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutinesCore}"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.Kotlin.serialization}"
    }

    object Ktor {
        const val ktorCore = "io.ktor:ktor-client-core:${Versions.Ktor.ktor}"
        const val ktorCio = "io.ktor:ktor-client-cio:${Versions.Ktor.ktor}"
        const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.Ktor.ktor}"
        const val ktorAuth = "io.ktor:ktor-client-auth:${Versions.Ktor.ktor}"
        const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.Ktor.ktor}"
        const val ktorJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.Ktor.ktor}"
    }

    object Kodein {
        const val kodein = "org.kodein.di:kodein-di:${Versions.Kodein.kodein}"
    }

    object Moko {
        const val mvvmCore = "dev.icerock.moko:mvvm-core:${Versions.Moko.moko}"
        const val mvvmFlow = "dev.icerock.moko:mvvm-flow:${Versions.Moko.moko}"
    }

    object Datastore {
        const val core = "androidx.datastore:datastore-preferences-core:${Versions.Datastore.datastore}"
    }

    object SQLDelight {
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.SQLDelight.sqlDelight}"
    }
}