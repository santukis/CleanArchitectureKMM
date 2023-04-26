import com.santukis.buildsrc.modules.Modules

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

apply(from = "$rootDir/android/gradle/configuration/base_android_library_module.gradle")

android {
    namespace = "com.santukis.home"
}

dependencies {
    implementation(project(Modules.ViewModel.coreViewModel))
    implementation(project(Modules.ViewModel.homeViewModel))
    implementation(project(Modules.entities))

    implementation(project(Modules.Android.Theme))
    implementation(project(Modules.Android.Navigation))
    implementation(project(Modules.Android.Widgets))
}