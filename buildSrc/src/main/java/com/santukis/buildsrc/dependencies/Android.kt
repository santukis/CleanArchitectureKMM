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
}