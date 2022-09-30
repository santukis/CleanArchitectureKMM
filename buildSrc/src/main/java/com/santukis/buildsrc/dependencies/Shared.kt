package com.santukis.buildsrc.dependencies

import com.santukis.buildsrc.versions.Versions

object Shared {

    object Test {
        const val junit4 = "junit:junit:${Versions.Test.junit4}"
        const val junit5 = "org.junit.jupiter:junit-jupiter:${Versions.Test.junit5}"
        const val mockK = "io.mockk:mockk:${Versions.Test.mockk}"
        const val mockKJvm = "io.mockk:mockk-agent-jvm:${Versions.Test.mockk}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.Squareup.okhttp}"
    }

    object Kotlin {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutinesCore}"
    }
}