package com.santukis.buildsrc.dependencies

import com.santukis.buildsrc.versions.Versions

object Android {

    object Test {
        const val junit4 = "androidx.test.ext:junit:${Versions.AndroidTest.junit4}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.AndroidTest.espressoCore}"
        const val composeUi = "androidx.compose.ui:ui-test-junit4:${Versions.Androidx.compose}"
        const val mockKAndroid = "io.mockk:mockk-android:${Versions.Test.mockk}"
        const val room = "androidx.room:room-testing:${Versions.Androidx.room}"
    }

    object Kotlin {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutinesCore}"
    }

    object Ktor {
        const val ktorOkhttp = "io.ktor:ktor-client-okhttp:${Versions.Ktor.ktor}"
    }

    object Kodein {
        const val kodein = "org.kodein.di:kodein-di-framework-android-x:${Versions.Kodein.kodein}"
    }

    object Androidx {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.Androidx.appcompat}"
        const val core = "androidx.core:core-ktx:${Versions.Androidx.core}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Androidx.lifecycle}"
        const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Androidx.lifecycle}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.Androidx.activityCompose}"
        const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.Androidx.compose}"
        const val composeUi = "androidx.compose.ui:ui:${Versions.Androidx.compose}"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.Androidx.compose}"
        const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.Androidx.compose}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.Androidx.compose}"
        const val composeMaterialIcons = "androidx.compose.material:material-icons-core:${Versions.Androidx.compose}"
        const val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.Androidx.compose}"
        const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.Androidx.composeConstraintLayout}"
        const val composeUtil = "androidx.compose.ui:ui-util:${Versions.Androidx.compose}"
        const val encryptedPreferences = "androidx.security:security-crypto:${Versions.Androidx.encryptedPreferences}"
    }

    object Moko {
        const val mvvmCompose = "dev.icerock.moko:mvvm-flow-compose:${Versions.Moko.moko}"
    }

    object Coil {
        const val coil = "io.coil-kt:coil-compose:${Versions.Coil.coil}"
    }

    object Snapper {
        const val snapper = "dev.chrisbanes.snapper:snapper:${Versions.Snapper.snapper}"
    }
}