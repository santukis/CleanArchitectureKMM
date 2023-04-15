import com.santukis.buildsrc.modules.Modules

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "$rootDir/android/gradle/configuration/base_android_library_module.gradle")

android {
    namespace = "com.santukis.moviedetail"
}

dependencies {
    implementation(project(Modules.ViewModels))
    implementation(project(Modules.Entities))

    implementation(project(Modules.Android.Theme))
    implementation(project(Modules.Android.Navigation))
    implementation(project(Modules.Android.UiEvents))
    implementation(project(Modules.Android.Widgets))
}