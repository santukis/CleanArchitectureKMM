import com.santukis.buildsrc.dependencies.Android
import com.santukis.buildsrc.modules.Modules

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "$rootDir/android/gradle/configuration/base_android_library_module.gradle")

android {
    namespace = "com.santukis.widgets"
}

dependencies {
    implementation(project(Modules.entities))

    implementation(Android.Media.exoplayer)
    implementation(Android.Media.exoplayerUi)
    implementation(Android.Media.youtubePlayer)
}