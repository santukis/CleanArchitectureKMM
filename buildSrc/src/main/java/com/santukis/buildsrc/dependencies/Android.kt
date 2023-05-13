package com.santukis.buildsrc.dependencies

import com.santukis.buildsrc.versions.Versions

object Android {

    object Test {
        const val junit4 = "androidx.test.ext:junit:${Versions.AndroidTest.junit4}"
        const val androidCoreTesting = "androidx.arch.core:core-testing:${Versions.AndroidTest.coreTesting}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.AndroidTest.espressoCore}"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.AndroidTest.espressoCore}"
        const val mockKAndroid = "io.mockk:mockk-android:${Versions.Test.mockk}"
        const val room = "androidx.room:room-testing:${Versions.Androidx.room}"

        const val composeTestUi = "androidx.compose.ui:ui-test"
        const val composeUiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
        const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
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

    object Koin {
        const val koin = "io.insert-koin:koin-android:${Versions.Koin.koin}"
    }

    object Androidx {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.Androidx.appcompat}"
        const val core = "androidx.core:core-ktx:${Versions.Androidx.core}"
        const val encryptedPreferences = "androidx.security:security-crypto:${Versions.Androidx.encryptedPreferences}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Androidx.lifecycle}"
        const val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Androidx.lifecycle}"

        const val composeBom = "androidx.compose:compose-bom:${Versions.Androidx.composeBoom}"
        const val composeRuntime = "androidx.compose.runtime:runtime"
        const val composeUi = "androidx.compose.ui:ui"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling"
        const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val composeFoundation = "androidx.compose.foundation:foundation"
        const val composeFoundationLayout = "androidx.compose.foundation:foundation-layout"
        const val composeMaterial = "androidx.compose.material:material"
        const val composeMaterialIcons = "androidx.compose.material:material-icons-core"
        const val composeMaterialIconsExtended = "androidx.compose.material:material-icons-extended"
        const val composeUtil = "androidx.compose.ui:ui-util"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.Androidx.activityCompose}"
        const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.Androidx.composeConstraintLayout}"
        const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.Androidx.composeNavigation}"
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

    object SQLDelight {
        const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.SQLDelight.sqlDelight}"
    }

    object Media {
        const val exoplayer = "androidx.media3:media3-exoplayer:${Versions.Media.exoplayer}"
        const val exoplayerUi = "androidx.media3:media3-ui:${Versions.Media.exoplayer}"
        const val youtubePlayer = "com.pierfrancescosoffritti.androidyoutubeplayer:core:${Versions.Media.youtubePlayer}"
    }
}