import com.santukis.buildsrc.modules.Modules

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "$rootDir/android/gradle/configuration/base_android_library_module.gradle")

android {
    namespace = "com.santukis.movies"
}

dependencies {
    implementation(project(Modules.ViewModel.coreViewModel))
    implementation(project(Modules.ViewModel.moviesViewModel))
    implementation(project(Modules.entities))

    implementation(project(Modules.Android.core))
    implementation(project(Modules.Android.theme))
    implementation(project(Modules.Android.navigation))
    implementation(project(Modules.Android.widgets))
}