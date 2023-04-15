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

    implementation(project(Modules.Injection))
    implementation(project(Modules.ViewModels))
    implementation(project(Modules.Entities))

    implementation(project(Modules.Android.Theme))
    implementation(project(Modules.Android.Navigation))
    implementation(project(Modules.Android.UiEvents))
    implementation(project(Modules.Android.Widgets))
    implementation(project(Modules.Android.Home))
    implementation(project(Modules.Android.MovieDetail))
}