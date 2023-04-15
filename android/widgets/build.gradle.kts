import com.santukis.buildsrc.dependencies.Android

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "$rootDir/android/gradle/configuration/base_android_library_module.gradle")

android {
    namespace = "com.santukis.widgets"
}

dependencies {
    implementation(Android.Media.exoplayer)
    implementation(Android.Media.exoplayerUi)
    implementation(Android.Media.youtubePlayer)
}