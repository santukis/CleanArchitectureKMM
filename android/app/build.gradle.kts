import com.santukis.buildsrc.modules.Modules

plugins {
    id("com.android.application")
    kotlin("android")
}

apply(from ="$rootDir/android/gradle/configuration/base_android_application_module.gradle")

android {
    namespace = "com.santukis.cleanarchitecturekmm.android"

    defaultConfig {
        applicationId = "com.santukis.cleanarchitecturekmm.android"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(fileTree(baseDir = "libs"))

    implementation(project(Modules.injection))
    implementation(project(Modules.ViewModel.coreViewModel))
    implementation(project(Modules.ViewModel.homeViewModel))
    implementation(project(Modules.ViewModel.movieDetailViewModel))
    implementation(project(Modules.entities))

    implementation(project(Modules.Android.Theme))
    implementation(project(Modules.Android.Navigation))
    implementation(project(Modules.Android.Widgets))
    implementation(project(Modules.Android.Home))
    implementation(project(Modules.Android.MovieDetail))
}